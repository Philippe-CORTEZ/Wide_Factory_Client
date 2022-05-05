package fr.univtln.wf.databases.daos;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Generic DAO which describe the common operations that all DAOs have
 * @param <E> generic type for entity
 * @author Wide Factory Team
 */
public abstract class GenericDAO<E> implements DAO<E>
{
    /** The nale of persist unit (described in persistence.xml) */
    // This way to do is correct if all DAO work in the same persist unit
    private static final String PERSISTENCE_UNIT_NAME = "wf";


    /** L'entity manager to manage entities with the database */
    protected final EntityManager entityManager;

    /** Transaction manager */
    protected final EntityTransaction transaction;

    /** Used to store the real type of the sub class DAO (avoid to specify in find request) */
    protected final Class<E> type;



    /** Constructor */
    protected GenericDAO()
    {
        // Define entity manager and transaction
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();

        // Get the real type of the sub class DAO
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.type = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }


    /**
     * Persist an entity in database and return after persisting
     * Equal to persist of entity manager
     * @param entity entity to persist
     * @return entity after persisting
     */
    @Override
    public E persist(E entity)
    {
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();

        // Update the entity with modification performed by the database (like auto generated id)
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * Return an entity which the primary key is specified
     * Equal to a find of entity manager
     * @param id the primary key
     * @return object of type E
     */
    @Override
    public E find(Object id)
    {
        return entityManager.find(type, id);
    }

    /**
     * Refresh an entity JAVA specified with the data from database
     * Equals to a refresh of entity manager
     * @param entity entity to refresh
     * @return object of type E
     */
    @Override
    public E refresh(E entity)
    {
        // refresh and return entity JAVA from the database
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * Delete an entity from the database
     * Equal to remove of entity manager
     * Equivalent au remove de l'entity manager
     * @param entity l'entité à supprimer
     */
    @Override
    public void remove(E entity)
    {
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
    }

    /**
     * Update entity from the database
     * Perform a merge in a transaction
     * All modification will be caught and processed by the entity manager
     * @param entity entity that will be updated in database
     */
    @Override
    public void update(E entity)
    {
        // In fact merge is used to ask to the entity manager to manage an entity non followed
        // But it can used in this configuration to caught any change in the entity
        // To directly update entity in database
        transaction.begin();
        entityManager.merge(entity);
        transaction.commit();
    }

    /**
     * Ask to entity manager to manage the given entity
     * Equal to merge of entity manager
     * @param entity entity to manage
     * @return entity that look like, but which is managed by the entity manager
     */
    @Override
    public E merge(E entity)
    {
        return entityManager.merge(entity);
    }

    /**
     * Get all entity from a particular type
     * @return list of entities
     */
    @Override
    public List<E> findAll()
    {
        // excerpt provided M.BRUNO example
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(type);
        Root<E> rootEntry = cq.from(type);
        CriteriaQuery<E> all = cq.select(rootEntry);

        TypedQuery<E> allQuery = entityManager.createQuery(all);

        return  allQuery.getResultList();
    }

}
