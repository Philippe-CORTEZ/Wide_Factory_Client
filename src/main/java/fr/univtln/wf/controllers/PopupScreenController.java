package fr.univtln.wf.controllers;

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
        //TODO show animation from the database
    }

    public void initDescription()
    {
        //TODO fill the decsription with the selected exercise description

        execiseDescription.setText("description : " + DataGUI.getExerciseSelected().getDescription());
    }

}
