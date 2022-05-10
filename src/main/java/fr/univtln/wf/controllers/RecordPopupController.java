package fr.univtln.wf.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RecordPopupController {

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

    public void cancelRecording() {

    }

    public void restartRecording() {

    }

     public void validateRecording() {

    }

    public void visualizeRecording() {

    }

    public void loading() {
        new Thread() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        restartBtn.setDisable(true);
                        cancelBtn.setDisable(true);
                        validateBtn.setDisable(true);
                        visualizeBtn.setDisable(true);
                    }
                });
                try {
                    Thread.sleep(3000); //5 seconds, obviously replace with your chosen time
                }
                catch(InterruptedException ex) {
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        restartBtn.setDisable(false);
                        cancelBtn.setDisable(false);
                        visualizeBtn.setDisable(false);
                        validateBtn.setDisable(false);
                        recordingLabel.setText("");
                    }
                });
            }
        }.start();
    }

}
