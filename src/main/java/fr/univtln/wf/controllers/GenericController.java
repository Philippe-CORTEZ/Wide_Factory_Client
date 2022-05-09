package fr.univtln.wf.controllers;

import fr.univtln.wf.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
     * Load a fxml file and display it
     * @param fxmlFileName the fxml file path
     */
    public void loadFxml(String fxmlFileName)
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

}
