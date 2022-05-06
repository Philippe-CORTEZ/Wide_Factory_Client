package fr.univtln.wf.controllers;

import fr.univtln.wf.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CoachController extends Application {
    @FXML
    private AnchorPane exercisesstack;

    @FXML
    private TextField exercicename;

    @FXML
    private Button exercicebutton;

    @FXML
    private TextArea exercisedescription;




    @FXML
    private Button logoutbutton;

    @FXML
    private Button recordbutton;

    @FXML
    private AnchorPane recordstack;






    //this function is called when clicking on start recording
    @FXML
    public void startrecording(){

        /*String name = exercicename.textProperty().getValue(); //this gets the exercise name written by the user
        String description = exercisedescription.textProperty().getValue();//this gets the exercise description written by the user
        System.out.println(name);
        System.out.println(description);
*/
        //TODO insert thes valuse in the data base and tell the c++ server when tob start recording and when to end it

        //Main.main(null); uncomment if you want to start recording when clicking start

    }


    /*
    this function is used to navigate
    when clicking record (recordbutton) we get sent to the record scene (recordstack)
    when we click exercises list (exercicebutton) we get sent to the exercises list scene (exercisesstack)
    when we click Logout we close the stage
     */

    @FXML
    void handleclicks(ActionEvent event) {
        if (event.getSource()==recordbutton){
            exercisesstack.toBack();
            recordstack.toFront();

        }
        if (event.getSource()==exercicebutton){

            exercisesstack.toFront();
            recordstack.toBack();

        }
        if (event.getSource()==logoutbutton){

                Stage stage = (Stage) logoutbutton.getScene().getWindow();
                stage.close();


        }

    }


    @Override
    public void start(Stage stage) throws Exception {


    }
}
