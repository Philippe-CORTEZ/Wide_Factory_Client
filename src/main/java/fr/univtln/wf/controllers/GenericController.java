package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * Mutualise common behaviour between controllers
 * @author Wide Factory Team
 */
@Slf4j
public abstract class GenericController
{
    /**
     * Load a fxml file and display it, replace the current FXML of the primary stage
     * @param fxmlFileName the fxml file path
     */
    public void changeFXML(String fxmlFileName)
    {
        try
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource(fxmlFileName)));
            Scene scene = new Scene(root);

            App.getStage().setScene(scene);
            App.getStage().sizeToScene();
        }
        catch (IOException e)
        {
            log.error("Unable to load the file specified", e);
        }
    }

    /**
     * Load a fxml file and display it, create a window popup
     * @param fxmlFileName the fxml file path
     * @param title the title of the window
     * @param undecorated if true the window is undecorated (without red cross, minimize and maximize button)
     */
    public void createPopup(String fxmlFileName, String title, boolean undecorated)
    {
        try
        {
            // This is a popup, so, a stage is created
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFileName)));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            // if true remove red cross, minimize and maximize button
            if(undecorated)
            {
                stage.initStyle(StageStyle.UNDECORATED);
            }

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            log.error("Unable to load the file specified", e);
        }
    }

    /**
     * Load a fxml file and display it, create a window popup with default decoration
     * @param fxmlFileName the fxml file path
     * @param title the title of the window
     */
    public void createPopup(String fxmlFileName, String title)
    {
        createPopup(fxmlFileName, title, false);
    }

    /**
     * Load a fxml file and display it, create a window popup with a default title and default decoration
     * @param fxmlFileName the fxml file path
     */
    public void createPopup(String fxmlFileName)
    {
        createPopup(fxmlFileName, "Wide Factory");
    }

}
