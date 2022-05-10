package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.models.Exercise;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;


/**
 * Controller that manage the main view of a coach user
 * @author Wide Factory Team
 */
@Slf4j
public class CoachController extends GenericController
{
    @FXML
    private AnchorPane exercisesStack;

    @FXML
    private ListView<Exercise> listOfExercises;

    @FXML
    private AnchorPane recordStack;


    /** Initialize the widgets wanted */
    @FXML
    public void initialize()
    {
        initListOfExercises();
    }

    /** this function is called when clicking on start recording button */
    @FXML
    public void startRecording()
    {
        /*String name = exercicename.textProperty().getValue(); //this gets the exercise name written by the user
        String description = exercisedescription.textProperty().getValue();//this gets the exercise description written by the user
        System.out.println(name);
        System.out.println(description);

*/

        createPopup("/view/fxml/recordpopup.fxml");
        //TODO insert these values in the data base and tell the c++ server when tob start recording and when to end it

       // Main.main(null); //uncomment if you want to start recording when clicking start
    }


    /** Method called when the user click on logout button */
    public void logout()
    {
        changeFXML("view/fxml/login.fxml");
    }

    /** Display the exercises list and hide recording screen */
    public void displayExercisesList()
    {
        exercisesStack.toFront();
        recordStack.toBack();
    }

    /** Display the recording screen and hide the exercises list view */
    public void displayRecordView()
    {
        exercisesStack.toBack();
        recordStack.toFront();
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

        // Add biding
        listOfExercises.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super Exercise>) (observable, oldValue, newValue) -> {
            DataGUI.setExerciseSelected(listOfExercises.getSelectionModel().selectedItemProperty().getValue());
            switchToPopupScene();
        });
    }

    public void switchToPopupScene()
    {
       createPopup("/view/fxml/popupscreen.fxml");
    }

}
