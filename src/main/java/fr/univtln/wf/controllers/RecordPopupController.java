package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.ws_clients.WSClient;
import fr.univtln.wf.ws_clients.WSState;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Controller to manage a movement recorded
 * @author Wide Factory Team
 */
@Slf4j
public class RecordPopupController
{
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label recordingLabel;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button restartBtn;

    @FXML
    private Button validateBtn;

    @FXML
    private Button visualizeBtn;

    @FXML
    private AnchorPane anchorPane;

    /** Used to move window */
    private double xOffset = 0;
    private double yOffset = 0;

    /** Animation of progressBar */
    Timeline timeline;



    /** Initialize widgets */
    @FXML
    public void initialize()
    {
        initializeProgressBar();

        // Make window draggable (because undecorated)
        makeDraggable();

        // Start recording directly
        startRecording();
    }

    /** Put websocket client in recording mode and ask to kinect to record movement */
    public void startRecording()
    {
        try
        {
            // Sending record message to server with time to record
            WSClient.getSession().getBasicRemote().sendText("r " + DataGUI.getTimeRecording());
        }
        catch (IOException error)
        {
            log.error("Error while sending message to server with WS client", error);
        }
        // Set the state of client websocket to recording, to record movement
        WSClient.setState(WSState.RECORDING);

        // Set name and description to the movement
        WSClient.getSTATIC_JME().getMv().getMovement().setName(DataGUI.getMovementNameRecording());
        WSClient.getSTATIC_JME().getMv().getMovement().setDescription(DataGUI.getMovementDescriptionRecording());
    }

    /** close the popup and clear the movement in memory */
    public void cancelRecording()
    {
        // Close window reset data from WS client
        ((Stage)(cancelBtn.getScene().getWindow())).close();
        WSClient.getSTATIC_JME().getMv().clear();
        WSClient.setState(WSState.STANDBY);
    }

    /** restart the record */
    public void restartRecording()
    {
        // Clear the skeleton recorded with kinect
        WSClient.getSTATIC_JME().getMv().getMovement().getSkeletons().clear();

        // Reset widget (button, progress bar)
        initializeProgressBar();
        resetButtons();

        // Start another recording
        startRecording();
    }

    /** persist the data record earlier and close the window */
     public void uploadRecording()
     {
         // Persist if the movement is not empty of skeletons
         if (!WSClient.getSTATIC_JME().getMv().getMovement().getSkeletons().isEmpty())
         {
             new MovementDAO().persist(WSClient.getSTATIC_JME().getMv().getMovement());
         }
         // Clear the movement to record another movement in the futur
         WSClient.getSTATIC_JME().getMv().clear();

         // Close the popup window
         ((Stage)(validateBtn.getScene().getWindow())).close();
    }

    /** Visualize the movement in memory in Jmonkey application */
    public void visualizeRecording()
    {
        WSClient.getSTATIC_JME().start();
    }

    /** Add possibility to drag window by his anchorPane */
    public void makeDraggable()
    {
       anchorPane.setOnMousePressed(event ->
       {
           xOffset = event.getSceneX();
           yOffset = event.getSceneY();
       });

        anchorPane.setOnMouseDragged(event ->
        {
            anchorPane.getScene().getWindow().setX(event.getScreenX() - xOffset);
            anchorPane.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });
    }


    private void initializeProgressBar()
    {
        // Default color of label and progress bar
        recordingLabel.setTextFill(Color.rgb(128, 128, 128));
        recordingLabel.setText("Recording ...");

        progressBar.setStyle("-fx-accent: #1E90FF;");

        // Initialize progress bar and bind event when is filled
        IntegerProperty seconds = new SimpleIntegerProperty();
        progressBar.progressProperty().bind(seconds.divide(60.0));
        timeline = new Timeline
                (
                        new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
                        new KeyFrame(Duration.seconds(DataGUI.getTimeRecording()), event ->
                        {
                            // When progress bar is filled (progression finished)
                            restartBtn.setDisable(false);
                            cancelBtn.setDisable(false);
                            validateBtn.setDisable(false);
                            visualizeBtn.setDisable(false);
                            recordingLabel.setText("Finished");
                            recordingLabel.setTextFill(Color.rgb(96,207,21));
                            progressBar.setStyle("-fx-accent: #60CF15 ;");
                        }, new KeyValue(seconds, 60))
                );

        timeline.play();
    }


    private void resetButtons()
    {
        validateBtn.setDisable(true);
        visualizeBtn.setDisable(true);
        restartBtn.setDisable(true);
    }
}
