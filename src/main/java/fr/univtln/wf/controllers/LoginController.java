package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Controller that manage the authentification
 * @author Wide Factory Team
 */
@Slf4j
public class LoginController extends Application
{
    static  Stage   coachstage = new Stage();

    @FXML
    private AnchorPane loginpage;

    @FXML
    private StackPane loginstackpane;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button cancelbutton;


    @Override
    public void start(Stage stage) throws Exception
    {
    }

    @FXML
    void switchtologinpage()
    {
        loginpage.toFront();
    }


    @FXML
    public void login()
    {
        //TODO send user to the right screen (check if its a coach or a regular user)

        //String user = username.textProperty().getValue();
        //String passwordd = password.textProperty().getValue();
        switchtocachscene();
    }


    @FXML
    void close(ActionEvent event)
    {
        if (event.getSource()==cancelbutton)
        {
            Stage stage = (Stage) cancelbutton.getScene().getWindow();
            stage.close();
        }
    }

    public void switchtocachscene()
    {
        // We change the display if it's ok
        try
        {
            Parent root = FXMLLoader.load(App.class.getClassLoader().getResource("view/fxml/coachscreen.fxml"));

            Scene scene = new Scene(root, 1305, 782);

            coachstage.setScene(scene);
            coachstage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}
