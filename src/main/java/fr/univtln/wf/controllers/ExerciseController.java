package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.Visualize;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class ExerciseController implements Controller
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
    public void composition()
    {
        createPopup("/view/fxml/ExerciseComposition.fxml", "Composition of exercise");
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
    public void start()
    {
        new Visualize().visualizeExercise(DataGUI.getExerciseSelected());
    }

}
