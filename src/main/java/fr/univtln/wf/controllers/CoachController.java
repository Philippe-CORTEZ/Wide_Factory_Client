package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import fr.univtln.wf.Main;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.models.Exercise;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CoachController extends Application  implements Initializable {
    @FXML
    private AnchorPane exercisesstack;

    @FXML
    private TextField exercicename;


    static  Stage   popupstage = new Stage();


    @FXML
    private ListView<String> listofexercises;

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

       // Main.main(null); //uncomment if you want to start recording when clicking start

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


    public ObservableList<String> shoexercises() {
        ObservableList<String> etudiants = null;
        ExerciseDAO d = new ExerciseDAO();


        etudiants = FXCollections.observableArrayList(d.getalexercises());



        return etudiants;
    }
    public void initlistofexercises(){


        ObservableList<String> list = shoexercises();

        listofexercises.setItems(list);
        /*
        listofexercises.setCellFactory(stringListView -> {
            ListCell c = new ListCell();
            c.setOnMouseClicked(mouseEvent -> {
                if (! c.isEmpty() && mouseEvent.getButton()== MouseButton.PRIMARY
                        && mouseEvent.getClickCount() == 2) {

                    System.out.println("hahahaha");
                }
            });
            return c;
        });
*/
        listofexercises.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super String>) (observable, oldValue, newValue) -> {
            // Your action here
            System.out.println("Selected item: " + newValue);
            switchtopopupscene();
        });



    }


    public void switchtopopupscene(){
        try {
            Parent root = FXMLLoader.load(App.class.getClassLoader().getResource("view/fxml/popupscreen.fxml"));

            Scene scene = new Scene(root, 521, 240);
            //popupstage.initStyle(StageStyle.UNDECORATED);
            popupstage.setScene(scene);
            popupstage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    @Override
    public void start(Stage stage) throws Exception {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initlistofexercises();
    }
}
