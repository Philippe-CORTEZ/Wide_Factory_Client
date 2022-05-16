package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.MovementDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;


/**
 * Controller that manage the main view as a coach user
 * It's a MainViewController with more possibilities like recording
 * @author Wide Factory Team
 */
@Slf4j
public class CoachController extends MainController
{

    @FXML
    private Label errorLabel;

    @FXML
    private AnchorPane exercisesStack;

    @FXML
    private AnchorPane recordStack;

    @FXML
    private Spinner<Integer> timeRecordingSpinner;

    @FXML
    private TextField movementName;

    @FXML
    private TextArea movementDescription;


    /** Initialize the widgets wanted */
    @Override
    @FXML
    public void initialize()
    {
        super.initialize();

        // Initialize spinner timer (2 to 10, initial 2, and change value 1 by 1)
        timeRecordingSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2, 1));

        displayExercisesList();
    }


    /** this function is called when clicking on start recording button */
    public void startRecording()
    {
        MovementDAO movementDAO = new MovementDAO();
        String name = movementName.getText();

        // Don't start recording if the name is empty or already taken
        if(name.isEmpty())
        {
            errorLabel.setText("Please enter a name");
        }
        else if(movementDAO.find(name)!=null)
        {
            errorLabel.setText("Try another movement's name, this name already exits ");
        }

        else
        {
            // Empty error label if it displayed
            errorLabel.setText("");

            // Create a popup to manage recording and set data used in popup
            DataGUI.setTimeRecording(timeRecordingSpinner.getValue());
            DataGUI.setMovementNameRecording(movementName.getText());
            DataGUI.setMovementDescriptionRecording(movementDescription.getText());
            createPopup("/view/fxml/recordpopup.fxml", "Recording", true);
        }
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

    public void addExercise() {
        createPopup("/view/fxml/addexercisepopup.fxml");
    }
}
