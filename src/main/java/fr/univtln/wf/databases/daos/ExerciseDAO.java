package fr.univtln.wf.databases.daos;

import fr.univtln.wf.models.Exercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO that manager Exercise class
 * @author Wide Factory Team
 */
public class ExerciseDAO extends GenericDAO<Exercise>
{
    EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("wf");
    EntityManager em = emf.createEntityManager();




    public List<String> getalexercises() {
        Query query = entityManager.createQuery("Select e.name from Exercise e");
        return query.getResultList();
    }


}
