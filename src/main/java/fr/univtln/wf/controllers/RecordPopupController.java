package fr.univtln.wf.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public void cancelRecording()
    {
//        cancelBtn.getParent().get
    }

    public void restartRecording() {

    }

     public void validateRecording() {

    }

    public void visualizeRecording() {

    }

    public void loading() {
        new Thread() {
            @Override
            public void run() {
                Platform.runLater( () ->
                        {
                            restartBtn.setDisable(true);
                            cancelBtn.setDisable(true);
                            validateBtn.setDisable(true);
                            visualizeBtn.setDisable(true);
                        }
                );
                try {
                    Thread.sleep(3000); // 3 seconds, obviously replace with your chosen time
                }
                catch(InterruptedException ex) {
                    log.error("Interruption error", ex);
                    Thread.currentThread().interrupt();
                }
                Platform.runLater( () ->
                        {
                            restartBtn.setDisable(false);
                            cancelBtn.setDisable(false);
                            visualizeBtn.setDisable(false);
                            validateBtn.setDisable(false);
                            recordingLabel.setText("");
                        }
                );
            }
        }.start();
    }

}
