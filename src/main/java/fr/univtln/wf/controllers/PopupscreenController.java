package fr.univtln.wf.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class PopupscreenController extends Application implements Initializable {

    @FXML
    private Label execisedesc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initdesc();
    }


    @FXML
    void visualize(ActionEvent event)
    {
        //TODO show animation from the database
    }

    public void initdesc()
    {
        //TODO fill the decsription with the selected exercise description

        execisedesc.setText("description : blablabla");
    }

    @Override
    public void start(Stage stage) throws Exception
    {
    }

}
