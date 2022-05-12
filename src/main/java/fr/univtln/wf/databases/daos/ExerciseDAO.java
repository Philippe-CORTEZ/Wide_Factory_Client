package fr.univtln.wf.databases.daos;

import fr.univtln.wf.models.Exercise;

/**
 * DAO that manager Exercise class
 * @author Wide Factory Team
 */
public class ExerciseDAO extends GenericDAO<Exercise>
{
    /**
     * Persist an entity in database and return after persisting
     * Equal to persist of entity manager
     * Add a mapping to his movements
     * @param entity entity to persist
     * @return entity after persisting
     */
    @Override
    public Exercise persist(Exercise entity)
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
