package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.ws_clients.WSClient;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordPopupController {

    @FXML
    private ProgressBar progressBar = new ProgressBar();

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
    public void initialize()
    {


        loading();
    }

    /** close the popup and clear the movement in memory */
    public void cancelRecording()
    {
        ((Stage)(cancelBtn.getScene().getWindow())).close();
        WSClient.getSTATIC_JME().getMv().clear();
    }

    /**
     * restart the record
     */
    public void restartRecording() {
        // TODO : restart recording with previous parameters
        WSClient.getSTATIC_JME().getMv().getMovement().getSkeletons().clear();
    }

    /**
     * persist the data record earlier
     */
     public void uploadRecording() {
         if (!WSClient.getSTATIC_JME().getMv().getMovement().getSkeletons().isEmpty())
         {
             new MovementDAO().persist(WSClient.getSTATIC_JME().getMv().getMovement());
         }
         WSClient.getSTATIC_JME().getMv().clear();
    }

    /**
     * visualize the movement in memory
     */
    public void visualizeRecording() {
        WSClient.getSTATIC_JME().start();
    }

    /**
     * Initialize the ProgressBar
     */
    public void loading() {
        IntegerProperty seconds = new SimpleIntegerProperty();
        progressBar.progressProperty().bind(seconds.divide(60.0));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
                new KeyFrame(Duration.seconds(DataGUI.getSpinnerValue()), e-> {
                    // do anything you need here on completion...
                    System.out.println("Minute over");
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

}
