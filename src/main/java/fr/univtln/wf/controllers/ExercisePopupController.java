package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.jme_apps.JMEExercise;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class ExercisePopupController
{
    @FXML
    private Label exerciseDescription;


    @FXML
    public void initialize()
    {
        exerciseDescription.setText("description : " + DataGUI.getExerciseSelected().getDescription());
    }

    /** Visualize reference exercise with reference movements */
    public void visualize()
    {
        JMEExercise jme = new JMEExercise();
        jme.getExoDisplayable().setExercise(DataGUI.getExerciseSelected());
        jme.start();
    }

}
