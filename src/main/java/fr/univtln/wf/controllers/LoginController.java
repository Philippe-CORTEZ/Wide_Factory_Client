package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import fr.univtln.wf.models.Person;
import fr.univtln.wf.models.RoleEnum;
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
    public void login()
    {
        // TODO : authentification + get the right user
        Person user = Person.builder().pseudo("Sketard").firstname("Zakk").lastname("Wide").roleEnum(RoleEnum.COACH).build();
        DataGUI.setCurrentUser(user);

        // If the user is a coach
        if(user.getRoleEnum().equals(RoleEnum.COACH))
        {
            switchToCoachScene();
        }

        // Else the user is a regular user
        else
        {
            switchToMainScene();
        }
    }


    /** Close the JavaFX stage when the button Exit is clicked */
    public void close()
    {
        App.getStage().close();
    }

    /** Change the element in the stage (login to main view as a coach) */
    public void switchToCoachScene()
    {
       changeFXML("view/fxml/CoachScreen.fxml");
    }

    /** Change the element in the stage (login to main view as a regular user) */
    public void switchToMainScene()
    {
        changeFXML("view/fxml/MainScreen.fxml");
    }

}
