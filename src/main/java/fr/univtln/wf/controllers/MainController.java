package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.models.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller of main view of this app
 * It display the main commands of a regular user (the commands of coach are detailed in CoachController)
 * @author Wide Factory Team
 */
public class MainController implements Controller
{
    @FXML
    private ListView<Exercise> listOfExercises;


    /** Initialize the widgets wanted */
    @FXML
    public void initialize()
    {
        // Get all exercises in database to display in exercises list
        initListOfExercises();
    }


    /** Method called when the user click on logout button */
    public void logout()
    {
        changeFXML("view/fxml/Login.fxml");
    }

    /** Get all exercises from database */
    public ObservableList<Exercise> getAllExercises()
    {
        ExerciseDAO exerciseDAO = new ExerciseDAO();

        return FXCollections.observableArrayList(exerciseDAO.findAll());
    }

    /** Initialize the list view exercise with the exercises in database */
    public void initListOfExercises()
    {
        ObservableList<Exercise> exercises = getAllExercises();
        listOfExercises.setItems(exercises);

        // Add binding on each item in listview (set exercise selected and unselect to reselect after popup closed)
        listOfExercises.setOnMouseClicked(event ->
        {
            if (listOfExercises.getSelectionModel().selectedItemProperty().getValue() != null)
            {
                DataGUI.setExerciseSelected(listOfExercises.getSelectionModel().selectedItemProperty().getValue());
                int index = listOfExercises.getSelectionModel().getSelectedIndex();
                listOfExercises.getSelectionModel().clearSelection(index);

                // Create a popup window to interact with the exercise selected */
                createPopup("/view/fxml/Exercise.fxml");
            }
        });
    }

}
