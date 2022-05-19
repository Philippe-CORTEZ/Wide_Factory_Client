package fr.univtln.wf.jmonkey;

import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeExercise;
import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeMovement;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.Movement;

public class Visualize {
    public void visualizeMovement(Movement movement)
    {
        JMEVisualizeMovement jme = new JMEVisualizeMovement();
        jme.getMv().setMovement(movement);
        jme.start();
    }

    public void visualizeExercise(Exercise exercise)
    {
        JMEVisualizeExercise jme = new JMEVisualizeExercise();
        jme.getExoDisplayable().setExercise(exercise);
        jme.start();
    }
}
