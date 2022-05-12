package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.ws_clients.WSClient;
import fr.univtln.wf.ws_clients.WSState;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


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

    @FXML
    private Spinner<Integer> timeRecordingSpinner;

    @FXML
    private TextField exerciseName;

    @FXML
    private TextArea exerciseDescription;


    /** Initialize the widgets wanted */
    @FXML
    public void initialize()
    {
        initListOfExercises();

        // Initialize spinner timer (2 to 10, initial 2, and change value 1 by 1)
        timeRecordingSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2, 1));
    }


    /** this function is called when clicking on start recording button */
    public void startRecording()
    {
        try
        {
            // Sending record message to server with time to record
            WSClient.getSession().getBasicRemote().sendText("r " + timeRecordingSpinner.getValue());
            DataGUI.setSpinnerValue(timeRecordingSpinner.getValue());
        }
        catch (IOException error)
        {
            log.error("Error while sending message to server with WS client", error);
        }
        // Set the state of client websocket to recording, to record movement
        WSClient.setState(WSState.RECORDING);

        // Set name and description to the movement
        WSClient.getSTATIC_JME().getMv().getMovement().setName(exerciseName.getText());
        WSClient.getSTATIC_JME().getMv().getMovement().setDescription(exerciseDescription.getText());

        createPopup("/view/fxml/recordpopup.fxml");
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

        // Add biding for each exercise when clicking on
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
