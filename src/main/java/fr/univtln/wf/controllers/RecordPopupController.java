package fr.univtln.wf.controllers;

import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.ws_clients.WSClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to manage a movement recorded
 * @author Wide Factory Team
 */
@Slf4j
public class RecordPopupController
{
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



    /** Initialize widgets */
    @FXML
    public void initialize()
    {
        loading();
        // make window draggable (because undecorated)
        makeDraggable();
    }

    /** close the popup and clear the movement in memory */
    public void cancelRecording()
    {
        ((Stage)(cancelBtn.getScene().getWindow())).close();
        WSClient.getSTATIC_JME().getMv().clear();
    }

    /** restart the record */
    public void restartRecording()
    {
        // TODO : restart recording with previous parameters
        WSClient.getSTATIC_JME().getMv().getMovement().getSkeletons().clear();
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


    /** Enable button */
    public void loading()
    {
        restartBtn.setDisable(false);
        cancelBtn.setDisable(false);
        validateBtn.setDisable(false);
        visualizeBtn.setDisable(false);
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
            ((Stage)(anchorPane.getScene().getWindow())).setX(event.getScreenX() - xOffset);
            ((Stage)(anchorPane.getScene().getWindow())).setY(event.getScreenY() - yOffset);
        });
    }

}
