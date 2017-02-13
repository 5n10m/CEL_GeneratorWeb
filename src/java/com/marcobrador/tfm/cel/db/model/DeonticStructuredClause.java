package com.marcobrador.tfm.cel.db.model;

import com.marcobrador.tfm.cel.db.Utils;
import com.marcobrador.tfm.cel.db.model.actions.*;
import com.marcobrador.tfm.cel.db.model.constraints.*;
import org.hibernate.annotations.ForceDiscriminator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

/**
 * Class that represents the cel-core:DeonticStructuredClauseType complex type.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "clause_type")
@ForceDiscriminator
@Table(name = "clauses")
public abstract class DeonticStructuredClause {

    @Id
    @Column
    @GeneratedValue
    @XmlTransient
    private int dbId;

    @Column
    @XmlAttribute
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElement(name = "PreCondition", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Set<PreCondition> preConditions;

    @Embedded
    @XmlElement(name="Subject", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Subject subject;

    @Embedded
    @XmlElement(name="Act", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Act act;

    @Embedded
    @XmlElement(name="Object", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private CelObject celObject;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElement(name="ResultantObject", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Item resultantObject;

    @Embedded
    @XmlElement(name="Constraint", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Constraint constraint;

    @Embedded
    @XmlElement(name="Issuer", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Issuer issuer;

    @ManyToOne
    private Contract contract;

    protected DeonticStructuredClause() {
        // Required by JAXB
    }

    protected DeonticStructuredClause(Builder builder) {
        id = builder.id;
        subject = builder.subject;
        act = builder.act;
        celObject = builder.object;
        constraint = builder.constraint;
        issuer = builder.issuer;
        resultantObject = builder.resultantObject;
    }

    public String getId() {
        return id;
    }

    public Act getAct() {
        return act;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public Set<PreCondition> getPreConditions() {
        return preConditions;
    }

    public Subject getSubject() {
        return subject;
    }

    public CelObject getCelObject() {
        return celObject;
    }

    public Item getResultantObject() {
        return resultantObject;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public Contract getContract() {
        return contract;
    }
    
    @Override
    public String toString() {
        String result = "id: " + id + "\n"
                + "Type: " + this.getClass().getSimpleName() + "\n"
                + act + "\n"
                + subject + "\n"
                + Utils.nullableToStringWithCr(preConditions)
                + Utils.nullableToStringWithCr(celObject)
                + (resultantObject == null ? "" : "Resultant Object{" + resultantObject + "}\n")
                + Utils.nullableToStringWithCr(constraint)
                + Utils.nullableToStringWithCr(issuer);
        // Strip possible CR at line ending
        if ("\n".equals(result.substring(result.length() - 1))) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        DeonticStructuredClause other = (DeonticStructuredClause) obj;
        return this.id.equals(other.id)
                && Utils.lazyCollectionCompare(this.preConditions, other.preConditions)
                && this.subject.equals(other.subject)
                && this.act.equals(other.act)
                && Utils.safeCompare(this.celObject, other.celObject)
                && compareConstraints(this.constraint, other.constraint)
                && Utils.safeCompare(this.issuer, other.issuer)
                && Utils.safeCompare(this.resultantObject, other.resultantObject);
    }

    @Override
    public int hashCode() {
        return id.hashCode()
                + Utils.computeNullableHashcode(preConditions)
                + subject.hashCode()
                + act.hashCode()
                + Utils.computeNullableHashcode(this.celObject)
                + Utils.computeNullableHashcode(this.constraint)
                + Utils.computeNullableHashcode(this.issuer)
                + Utils.computeNullableHashcode(this.resultantObject);
    }

    /**
     * Utility method for comparing constraints. If one of the two constraints is null and the other
     * one is not, the comparison will succeed if and only if the second one has an empty list of
     * facts.
     * In case both are null, comparison succeeds. In case both are not null, the result of
     * {@link com.marcobrador.tfm.cel.db.model.DeonticStructuredClause.Constraint#equals(Object)}
     * is returned.
     *
     * @param first The first parameter of the comparison.
     * @param second The second parameter of the comparison.
     *
     * @return {@code true} if both constraints are equal, {@code false} otherwise.
     */
    private boolean compareConstraints(Constraint first, Constraint second) {
        if (first == null && second == null) {
            return true;
        } else if (first == null) {
            return second.facts.isEmpty();
        } else if (second == null) {
            return first.facts.isEmpty();
        } else {
            return first.equals(second);
        }
    }

    /**
     * Abstract builder to be extended by the implementations of the {@link DeonticStructuredClause} class.
     */
    public static abstract class Builder {
        private final String id;
        private final Subject subject;
        private final Act act;
        private CelObject object;
        private Item resultantObject;
        private Constraint constraint;
        private Issuer issuer;

        public Builder(String id, Subject subject, Act act) {
            if (id == null) {
                throw new IllegalArgumentException("id cannot be null");
            }
            if (subject == null) {
                throw new IllegalArgumentException("sibject cannot be null");
            }
            if (act == null) {
                throw new IllegalArgumentException("act cannot be null");
            }
            this.id = id;
            this.subject = subject;
            this.act = act;
        }

        public Builder setObject(CelObject object) {
            this.object = object;
            return this;
        }

        public Builder setResultantObject(Item resultantObject) {
            this.resultantObject = resultantObject;
            return this;
        }

        public Builder setConstraint(Constraint constraint) {
            this.constraint = constraint;
            return this;
        }

        public Builder setIssuer(Issuer issuer) {
            this.issuer = issuer;
            return this;
        }

        public abstract DeonticStructuredClause build();
    }

    // Complex elements of DeonticStructuredClause

    /**
     * Class that represents the cel-core:Subject class.
     */
    @Embeddable
    public static class Subject {

        @Column(name = "subject")
        @XmlAttribute
        private String partyRef;

        // TODO: missing relationship with parties table

        private Subject() {
        }

        public Subject(String partyRef) {
            this.partyRef = partyRef;
        }

        public String getPartyRef() {
            return partyRef;
        }

        @Override
        public int hashCode() {
            return partyRef.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Subject)) {
                return false;
            }
            Subject other = (Subject) obj;
            return this.partyRef.equals(other.partyRef);
        }

        @Override
        public String toString() {
            return "Subject: " + partyRef;
        }
    }

    /**
     * Class that represents the cel-core:Issuer class.
     */
    @Embeddable
    public static class Issuer {

        @Column(name = "issuer")
        @XmlAttribute
        private String partyRef;

        // TODO: missing relationship with parties table

        private Issuer() {
        }

        public Issuer(String partyRef) {
            this.partyRef = partyRef;
        }

        public String getPartyRef() {
            return partyRef;
        }

        @Override
        public int hashCode() {
            return partyRef.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Issuer)) {
                return false;
            }
            Issuer other = (Issuer) obj;
            return this.partyRef.equals(other.partyRef);
        }

        @Override
        public String toString() {
            return "Issuer: " + partyRef;
        }
    }

    /**
     * Class that represents the cel-core:Act type.
     */
    @Embeddable
    public static class Act {
        @Column(name = "act_id")
        @XmlAttribute
        private String id;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @XmlElements(value = {
                // CEL CORE actions
                @XmlElement(name = "Consume", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Consume.class),
                @XmlElement(name = "Match", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Match.class),
                @XmlElement(name = "Provide", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Provide.class),
                // CEL IPRE actions
                @XmlElement(name = "Distribute", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Distribute.class),
                @XmlElement(name = "MakeCopy", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = MakeCopy.class),
                @XmlElement(name = "Fixate", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Fixate.class),
                @XmlElement(name = "CommunicationToThePublic", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = CommunicationToThePublic.class),
                @XmlElement(name = "Transform", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Transform.class),
                @XmlElement(name = "CreativeTransform", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = CreativeTransform.class),
                @XmlElement(name = "MakeExcerpt", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = MakeExcerpt.class),
                // CEL PANE actions
                @XmlElement(name = "Payment", namespace = "urn:mpeg:mpeg21:cel:pane:2015", type = Payment.class),
                @XmlElement(name = "Notify", namespace = "urn:mpeg:mpeg21:cel:pane:2015", type = Notify.class),
                // CEL RELE actions
                @XmlElement(name = "adapt", namespace = "urn:mpeg:mpeg21:2003:01-REL-MX-NS", type = Adapt.class),
                @XmlElement(name = "enhance", namespace = "urn:mpeg:mpeg21:2003:01-REL-MX-NS", type = Enhance.class),
                @XmlElement(name = "issue", namespace = "urn:mpeg:mpeg21:2003:01-REL-R-NS", type = Issue.class),
                @XmlElement(name = "modify", namespace = "urn:mpeg:mpeg21:2003:01-REL-MX-NS", type = Modify.class)
        })
        private Action action;

        private Act() {
            // Required by JAXB
        }

        /**
         * Creates a new instance of the {@link Act} class.
         *
         * @param id The id of the act. May be null.
         * @param action The of the act. Cannot be null.
         */
        public Act(String id, Action action) {
            if (action == null) {
                throw new IllegalArgumentException("action cannot be null");
            }
            this.id = id;
            this.action = action;
        }

        @Override
        public int hashCode() {
            return Utils.computeNullableHashcode(id) + action.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Act)) {
                return false;
            }
            Act other = (Act) obj;
            return Utils.safeCompare(this.id, other.id)
                    && this.action.equals(other.action);
        }

        @Override
        public String toString() {
            return "Act: " + action + (id == null ? "" : "(id: " + id +")");
        }

        public String getId() {
            return id;
        }
    }

    /**
     * Class that represents the cel-core:Object type.
     */
    @Embeddable
    public static class CelObject {

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @XmlElement(name="Item", namespace = "urn:mpeg:mpeg21:cel:core:2015")
        private Item item;

        private CelObject() {
            // Required by JAXB
        }

        public CelObject(Item item) {
            this.item = item;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof CelObject)) return false;

            CelObject celObject = (CelObject) o;

            return item != null ? item.equals(celObject.item) : celObject.item == null;
        }

        @Override
        public int hashCode() {
            return item != null ? item.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Object{" + item + "}";
        }
    }

    /**
     * Class that represents the cel-core:Constraint complex type.
     */
    @Embeddable
    public static class Constraint {

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @XmlElements(value = {
                // CEL CORE constraints
                @XmlElement(name = "ActionEventRelatedFact", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = ActionEventRelatedFact.class),
                @XmlElement(name = "FactIntersection", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactIntersection.class),
                @XmlElement(name = "FactNegation", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactNegation.class),
                @XmlElement(name = "FactUnion", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactUnion.class),
                @XmlElement(name = "TogetherWith", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = TogetherWith.class),
                // CEL IPRE constraints
                @XmlElement(name = "AccessPolicy", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = AccessPolicy.class),
                @XmlElement(name = "DeliveryModality", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = DeliveryModality.class),
                @XmlElement(name = "Device", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Device.class),
                @XmlElement(name = "Language", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Language.class),
                @XmlElement(name = "Length", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Length.class),
                @XmlElement(name = "MaterialFormat", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = MaterialFormat.class),
                @XmlElement(name = "Means", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Means.class),
                @XmlElement(name = "Runs", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Runs.class),
                @XmlElement(name = "SpatialContext", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = SpatialContext.class),
                @XmlElement(name = "TemporalContext", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = TemporalContext.class),
                // CEL RELE constraints
                @XmlElement(name = "feePerInterval", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = FeePerInterval.class),
                @XmlElement(name = "validityTimeMetered", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = ValidityTimeMetered.class),
                @XmlElement(name = "validityTimePeriodic", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = ValidityTimePeriodic.class),
                @XmlElement(name = "validityInterval", namespace = "urn:mpeg:mpeg21:2003:01-REL-R-NS", type = ValidityInterval.class)
        })
        private Set<Fact> facts;

        private Constraint() {
            // Required by JAXB
        }

        public Constraint(Fact fact) {
            if (fact == null) {
                throw new IllegalArgumentException("fact cannot be null");
            }
            facts = new HashSet<Fact>();
            facts.add(fact);
        }

        public Constraint(Set<Fact> facts) {
            if (facts == null) {
                throw new IllegalArgumentException("facts cannot be null");
            }
            if (facts.size() == 0) {
                throw new IllegalArgumentException("facts cannot be empty");
            }
            this.facts = facts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Constraint that = (Constraint) o;

            return facts.equals(that.facts);

        }

        @Override
        public int hashCode() {
            return facts.hashCode();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("Constraint{");
            for (Fact fact : facts) {
                builder.append("\n");
                builder.append(fact);
            }
            builder.append("\n}");
            return builder.toString();
        }
    }
}
