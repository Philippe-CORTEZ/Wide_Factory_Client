package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller that manage the authentification
 * @author Wide Factory Team
 */
@Slf4j
public class LoginController
{
    /** Used to get the password typed */
    @FXML
    private PasswordField password;

    /** Used to get the username typed */
    @FXML
    private TextField username;


    /** Method called when the authentification succeeded, it load user data and display main view */
    @FXML
    public void login()
    {
        //TODO send user to the right screen (check if its a coach or a regular user)

        //String user = username.textProperty().getValue();
        //String passwordd = password.textProperty().getValue();
        switchToCoachScene();
    }


    /** Close the JavaFX stage when the button Exit is clicked */
    @FXML
    void close()
    {
        App.getStage().close();
    }

    /** Change the element in the stage (login to main view) */
    public void switchToCoachScene()
    {
        try
        {
            // We change the scene by this new fxml file
            // Unable to simply change the rootNode because FXML is a different way to change screen
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("view/fxml/coachscreen.fxml")));
            Scene scene = new Scene(root, 1305, 782);

            App.getStage().setScene(scene);
            App.getStage().sizeToScene();
        }
        catch (IOException e)
        {
            log.error("Unable to load the file specified", e);
        }
    }

}
