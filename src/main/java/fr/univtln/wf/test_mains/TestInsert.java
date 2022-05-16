package fr.univtln.wf.test_mains;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.models.Movement;

import java.io.IOException;

public class TestInsert
{
    public static void main(String[] args) throws IOException
    {
        // Used to persist a movement
        Movement m = new Movement("src/main/resources/movement_coucou.json", "coucou");
        MovementDAO movementDAO = new MovementDAO();
        movementDAO.persist(m);
    }

}
