package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;


/**
 * Controller that manage the authentification
 * @author Wide Factory Team
 */
@Slf4j
public class LoginController implements Controller
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
       changeFXML("view/fxml/coachscreen.fxml");
    }

}
