package com.marcobrador.tfm.cel.db;

import com.marcobrador.tfm.cel.db.model.*;
import com.marcobrador.tfm.cel.db.model.actions.*;
import com.marcobrador.tfm.cel.db.model.constraints.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Wrapper for configuring, initializing, maintaining ad closing of Hibernate Sessions.
 */
public class HibernateSessionWrapper {

    private static HibernateSessionWrapper mInstance;
    private SessionFactory mSessionFactory;

    private HibernateSessionWrapper() {
        Configuration configuration = new Configuration()
                .configure()
                .addAnnotatedClass(Contract.class)
                .addAnnotatedClass(Party.class)
                .addAnnotatedClass(Body.class);
        addPartySubStructuresAnnotatedClasses(configuration);
        addBodySubStructuresAnnotatedClasses(configuration);
        mSessionFactory = configuration.buildSessionFactory();
    }

    /**
     * @return the Singleton instance of the {@link HibernateSessionWrapper} class.
     */
    public static HibernateSessionWrapper getInstance() {
        if (mInstance == null) {
            mInstance = new HibernateSessionWrapper();
        }
        return mInstance;
    }

    /**
     * @return the current hibernate session.
     */
    public Session getCurrentSession() {
        return mSessionFactory.getCurrentSession();
    }

    /**
     * Shuts down any hibernate connection to the DB.
     */
    public void shutdown() {
        mSessionFactory.close();
        mInstance = null;
    }

    private void addPartySubStructuresAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(PartyBasicGroup.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Organization.class);
    }

    private void addBodySubStructuresAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(OperativePart.class)
                .addAnnotatedClass(DeonticStructuredClause.class)
                .addAnnotatedClass(Permission.class)
                .addAnnotatedClass(Obligation.class)
                .addAnnotatedClass(Prohibition.class)
                .addAnnotatedClass(Statement.class)
                .addAnnotatedClass(IprePermission.class)
                // PreCondition had to be moved out of DeonticStructuredClause
                .addAnnotatedClass(PreCondition.class);
        addCelCoreActions(configuration);
        addCelCoreObjects(configuration);
        addCelCoreConstraints(configuration);
        addCelIpreActions(configuration);
        addCelIpreConstraints(configuration);
        addCelPaneActions(configuration);
        addCelReleActions(configuration);
        addCelReleConstraints(configuration);
    }

    private void addCelCoreActions(Configuration configuration) {
        configuration.addAnnotatedClass(Action.class)
                .addAnnotatedClass(Consume.class)
                .addAnnotatedClass(Match.class)
                .addAnnotatedClass(Provide.class);
    }

    private void addCelCoreObjects(Configuration configuration) {
        configuration.addAnnotatedClass(Item.class);
    }

    private void addCelCoreConstraints(Configuration configuration) {
        configuration.addAnnotatedClass(Fact.class)
                .addAnnotatedClass(ActionEventRelatedFact.class)
                .addAnnotatedClass(FactComposition.class)
                .addAnnotatedClass(FactIntersection.class)
                .addAnnotatedClass(FactNegation.class)
                .addAnnotatedClass(FactUnion.class)
                .addAnnotatedClass(TogetherWith.class);
    }

    private void addCelIpreActions(Configuration configuration) {
        configuration.addAnnotatedClass(Distribute.class)
                .addAnnotatedClass(MakeCopy.class)
                .addAnnotatedClass(Fixate.class)
                .addAnnotatedClass(CommunicationToThePublic.class)
                .addAnnotatedClass(Transform.class)
                .addAnnotatedClass(CreativeTransform.class)
                .addAnnotatedClass(MakeExcerpt.class);
    }

    private void addCelIpreConstraints(Configuration configuration) {
        configuration.addAnnotatedClass(AccessPolicy.class)
                .addAnnotatedClass(DeliveryModality.class)
                .addAnnotatedClass(Device.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Length.class)
                .addAnnotatedClass(MaterialFormat.class)
                .addAnnotatedClass(Means.class)
                .addAnnotatedClass(Runs.class)
                .addAnnotatedClass(SpatialContext.class)
                .addAnnotatedClass(TemporalContext.class);
    }

    private void addCelPaneActions(Configuration configuration) {
        configuration.addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Notify.class);
    }

    private void addCelReleActions(Configuration configuration) {
        configuration.addAnnotatedClass(Adapt.class)
                .addAnnotatedClass(Enhance.class)
                .addAnnotatedClass(Issue.class)
                .addAnnotatedClass(Modify.class);
    }

    private void addCelReleConstraints(Configuration configuration) {
        configuration.addAnnotatedClass(FeePerInterval.class)
                .addAnnotatedClass(ValidityTimeMetered.class)
                .addAnnotatedClass(ValidityTimePeriodic.class)
                .addAnnotatedClass(ValidityInterval.class);
    }
}
