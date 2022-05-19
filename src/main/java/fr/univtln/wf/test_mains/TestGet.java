package fr.univtln.wf.test_mains;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.jmonkey.Visualize;
import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeMovement;
import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;


public class TestGet
{
    public static void main(String[] args)
    {
        // Used to get and display a movement
        MovementDAO movementDAO = new MovementDAO();
        Movement m = movementDAO.find("coucou");


        for(Skeleton skeleton : m.getSkeletons())
        {
            skeleton.mappingJoint();
        }

        new Visualize().visualizeMovement(m);
    }

}
