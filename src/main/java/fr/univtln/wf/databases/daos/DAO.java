package fr.univtln.wf.databases.daos;

import java.util.List;

/**
 * Every class that implement DAO, have at least the CRUD operations
 * @author Wide Factory Team
 * @param <E> generic type
 */
public interface DAO<E>
{
    /**
     * Persist an entity in database and return after persisting
     * Equal to persist of entity manager
     * @param entity entity to persist
     * @return entity after persisting
     */
    E persist(E entity);

    /**
     * Return an entity which the primary key is specified
     * Equal to a find of entity manager
     * @param id the primary key
     * @return object of type E
     */
    E find(Object id);

    /**
     * Refresh an entity JAVA specified with the data from database
     * Equals to a refresh of entity manager
     * @param entity entity to refresh
     * @return object of type E
     */
    E refresh(E entity);

    /**
     * Delete an entity from the database
     * Equal to remove of entity manager
     * @param entity entity to remove
     */
    void remove(E entity);

    /**
     * Update entity from the database
     * Perform a merge in a transaction
     * All modification will be caught and processed by the entity manager
     * @param entity entity that will be updated in database
     */
    void update(E entity);

    /**
     * Ask to entity manager to manage the given entity
     * Equal to merge of entity manager
     * @param entity entity to manage
     * @return entity that look like, but which is managed by the entity manager
     */
    E merge(E entity);

    /**
     * Get all entity from a particular type
     * @return list of entities
     */
    List<E> findAll();
}
