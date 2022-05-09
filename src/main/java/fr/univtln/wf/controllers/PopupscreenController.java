package fr.univtln.wf.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupscreenController extends Application implements Initializable {

    @FXML
    private Label execisedesc;

    @FXML
    void visualize(ActionEvent event) {
        //TODO show animation from the database

    }

    public void initdesc(){
        //TODO fill the decsription with the selected exercise description

        execisedesc.setText("description : blablabla");
    }



    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initdesc();

    }
}
