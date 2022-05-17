package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeExercise;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class ExercisePopupController
{
    @FXML
    private Button deleteBtn;
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
        JMEVisualizeExercise jme = new JMEVisualizeExercise();
        jme.getExoDisplayable().setExercise(DataGUI.getExerciseSelected());
        jme.start();
    }

    /**
     * delete the exercise selected
     */
    public void delete()
    {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        DataGUI.setExerciseSelected(exerciseDAO.merge(DataGUI.getExerciseSelected()));
        exerciseDAO.remove(DataGUI.getExerciseSelected());
        ((Stage)(deleteBtn.getScene().getWindow())).close();
    }

    /**
     * start the exercise selected
     */
    public void start()
    {
        // TODO add special treatment for the exercise
        visualize();
    }

}
