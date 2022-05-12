package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.JMEExercise;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class PopupScreenController
{
    @FXML
    private Label execiseDescription;


    @FXML
    public void initialize()
    {
        initDescription();
    }

    /** Visualize reference exercise with reference movements */
    public void visualize()
    {
        JMEExercise jme = new JMEExercise();
        jme.getExoDisplayable().setExercise(DataGUI.getExerciseSelected());
        jme.start();
    }

    public void initDescription()
    {
        //TODO fill the decsription with the selected exercise description

        execiseDescription.setText("description : " + DataGUI.getExerciseSelected().getDescription());
    }

}
