package fr.univtln.wf.databases.daos;

import fr.univtln.wf.models.Skeleton;

/**
 * DAO to manage Skeleton objects
 * @author Wide Factory Team
 */
public class SkeletonDAO extends GenericDAO<Skeleton>
{
    /**
     * Persist an entity in database and return after persisting
     * Equal to persist of entity manager
     * Add a mapping to his joints
     * @param entity entity to persist
     * @return entity after persisting
     */
    @Override
    public Skeleton persist(Skeleton entity)
    {
        entity.mappingAttribute();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();

        // Update the entity with modification performed by the database (like auto generated id)
        entityManager.refresh(entity);
        return entity;
    }
}
