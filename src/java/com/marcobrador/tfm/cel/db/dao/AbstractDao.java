package com.marcobrador.tfm.cel.db.dao;

import com.marcobrador.tfm.cel.db.HibernateSessionWrapper;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Abstract DAO with basic functionality to be extended by all DAOs in the application.
 */
public abstract class AbstractDao<K extends Serializable, T> {

    private final Class<T> mPersistentClass;

    /**
     * Creates a new instance of the {@link AbstractDao} class.
     */
    @SuppressWarnings("unchecked")
    public AbstractDao() {
        mPersistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * @return The current hibernate session.
     */
    protected Session getSession() {
        return HibernateSessionWrapper.getInstance().getCurrentSession();
    }

    /**
     * Gets the entity wth the given key from the DB.
     *
     * @param key The key of the entity to be retrieved.
     *
     * @return The entity loaded from the DB.
     */
    public Object getByKey(K key) {
        Transaction tx = null;
        try {
            tx = getSession().beginTransaction();
            Object entity = getSession().get(mPersistentClass, key);
            tx.commit();
            return entity;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    /**
     * Saves the given entity to the DB.
     *
     * @param entity The entity to be persisted in the DB.
     *
     * @return The key with which the entity has been persisted in the DB.
     *
     * @throws HibernateException If te entity could not be persisted in the DB.
     */
    public K save(T entity) throws HibernateException {
        Transaction tx = null;
        try {
            tx = getSession().beginTransaction();
            @SuppressWarnings("unchecked")
            K id = (K) getSession().save(entity);
            tx.commit();
            return id;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    /**
     * Deletes the entity identified by the given key from the DB.
     *
     * @param key The key of the entity to be removed.
     */
    public void delete(K key) {
        delete((T) getByKey(key));
    }

    /**
     * Deletes the given entity from the DB.
     *
     * @param entity The entity to be removed.
     */
    public void delete(T entity) {
        Transaction tx = null;
        try {
            tx = getSession().beginTransaction();
            getSession().delete(entity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}
