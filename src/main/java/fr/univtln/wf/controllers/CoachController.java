package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller that manage the main view of a coach user
 * @author Wide Factory Team
 */
public class CoachController extends Application  implements Initializable
{
    static  Stage   popupstage = new Stage();

    @FXML
    private AnchorPane exercisesstack;

    @FXML
    private TextField exercicename;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initlistofexercises();
    }

    /** this function is called when clicking on start recording button */
    @FXML
    public void startrecording() {

        /*String name = exercicename.textProperty().getValue(); //this gets the exercise name written by the user
        String description = exercisedescription.textProperty().getValue();//this gets the exercise description written by the user
        System.out.println(name);
        System.out.println(description);
*/
        //TODO insert these values in the data base and tell the c++ server when tob start recording and when to end it

       // Main.main(null); //uncomment if you want to start recording when clicking start

    }

    /**
     *  This function is used to navigate
     *  when clicking record (recordbutton) we get sent to the record scene (recordstack)
     *  when we click exercises list (exercicebutton) we get sent to the exercises list scene (exercisesstack)
     *  when we click Logout we close the stage
     * @param event
     */
    @FXML
    void handleclicks(ActionEvent event)
    {
        if (event.getSource()==recordbutton)
        {
            exercisesstack.toBack();
            recordstack.toFront();

        }
        if (event.getSource()==exercicebutton)
        {
            exercisesstack.toFront();
            recordstack.toBack();
        }
        if (event.getSource()==logoutbutton)
        {
                Stage stage = (Stage) logoutbutton.getScene().getWindow();
                stage.close();
        }
    }

    public ObservableList<String> shoexercises()
    {
        ObservableList<String> etudiants = null;
        ExerciseDAO d = new ExerciseDAO();

        etudiants = FXCollections.observableArrayList(d.getalexercises());

        return etudiants;
    }

    public void initlistofexercises()
    {
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

    public void switchtopopupscene()
    {
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
    public void start(Stage stage) throws Exception
    {
    }

}
