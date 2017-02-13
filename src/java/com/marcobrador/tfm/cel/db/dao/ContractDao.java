package com.marcobrador.tfm.cel.db.dao;

import com.marcobrador.tfm.cel.db.model.Contract;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO for the {@link Contract} class.
 */
public class ContractDao extends AbstractDao<String, Contract> {

    public List<String> listContractIds() {
        Transaction tx = null;
        try {
            tx = getSession().beginTransaction();
            @SuppressWarnings("unchecked")
            List<String> contracts = getSession().createQuery("select contractId from Contract").list();
            tx.commit();
            return contracts;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}
