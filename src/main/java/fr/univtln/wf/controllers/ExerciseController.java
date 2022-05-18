package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.jme_apps.JMEStartExercise;
import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeExercise;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.ws_clients.WSData;
import fr.univtln.wf.ws_clients.WSState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class ExerciseController
{
    @FXML
    private Button deleteBtn;
    @FXML
    private Label exerciseDescription;


    /**
     * initialisation of the GUI
     */
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

    /** Delete the exercise selected */
    public void delete()
    {
        ExerciseDAO exerciseDAO = new ExerciseDAO();

        // Before removing the object, is necessary to merge to warn entity manager that object need to be manage
        DataGUI.setExerciseSelected(exerciseDAO.merge(DataGUI.getExerciseSelected()));
        exerciseDAO.remove(DataGUI.getExerciseSelected());
        ((Stage)(deleteBtn.getScene().getWindow())).close();
    }

    /** Start the exercise selected */
    public void start() throws IOException
    {
        // Set state to real time and call the right jmonkey app
        WSData.setState(WSState.REAL_TIME);
        WSData.getSession().getBasicRemote().sendText("r 15");
        JMEStartExercise jme = new JMEStartExercise();
        jme.getExerciseDisplayable().setExercise(DataGUI.getExerciseSelected());
        jme.start();
    }

}
