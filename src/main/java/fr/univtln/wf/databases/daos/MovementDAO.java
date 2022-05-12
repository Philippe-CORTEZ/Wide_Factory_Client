package fr.univtln.wf.databases.daos;

import fr.univtln.wf.models.Movement;

/**
 * DAO to manage Movement objects
 * @author Wide Factory Team
 */
public class MovementDAO extends GenericDAO<Movement>
{
    /**
     * Persist an entity in database and return after persisting
     * Equal to persist of entity manager
     * Add a mapping to his skeletons
     * @param entity entity to persist
     * @return entity after persisting
     */
    @Override
    public Movement persist(Movement entity)
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
