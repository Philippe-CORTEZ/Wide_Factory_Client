package fr.univtln.wf.controllers;

import fr.univtln.wf.TestInsert;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.jmonkey.JME;
import fr.univtln.wf.models.Movement;
import fr.univtln.wf.ws_clients.WSClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
        ((Stage)(cancelBtn.getScene().getWindow())).close();
        WSClient.getSTATIC_JME().getMv().clear();
    }

    public void restartRecording() {
        WSClient.getSTATIC_JME().getMv().clear();
    }

     public void uploadRecording() {
         new MovementDAO().persist(WSClient.getSTATIC_JME().getMv().getMovement());
         WSClient.getSTATIC_JME().getMv().clear();
    }

    public void visualizeRecording() {
        WSClient.getSTATIC_JME().start();
    }

    public void loading() {
        new Thread(() -> {
            Platform.runLater( () ->
                    {
                        restartBtn.setDisable(false);
                        cancelBtn.setDisable(false);
                        validateBtn.setDisable(false);
                        visualizeBtn.setDisable(false);
                    }
            );
        }).start();
    }

}
