package fr.univtln.wf;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.jmonkey.jme_apps.JME;
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

        JME jme = new JME();
        jme.getMv().setMovement(m);
        jme.start();
    }

}
