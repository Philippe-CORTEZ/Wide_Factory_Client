package fr.univtln.wf;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.jmonkey.JME;
import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;


public class TestGet
{
    public static void main(String[] args)
    {
        // Used to get and display a movement
        MovementDAO movementDAO = new MovementDAO();
        Movement m = movementDAO.find("pompe");


        for(Skeleton skeleton : m.getSkeletons())
        {
            System.out.println(skeleton.getFrame());
            skeleton.mappingJoint();
        }

        JME jme = new JME();
        jme.setMovement(m);
        jme.start();
    }

}