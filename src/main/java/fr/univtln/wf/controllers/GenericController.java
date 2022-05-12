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
     */
    public void createPopup(String fxmlFileName)
    {
        try
        {
            // This is a popup, so, a stage is created
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFileName)));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Exercise");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            log.error("Unable to load the file specified", e);
        }
    }


    /**
     * CreatePopup without red cross button
     * @param fxmlFileName the fxml file path
     */
    public void createPopupRedLess(String fxmlFileName)
    {
        // TODO : Need to be refactored
        try
        {
            // This is a popup, so, a stage is created
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFileName)));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Exercise");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            log.error("Unable to load the file specified", e);
        }
    }

}
