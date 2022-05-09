package fr.univtln.wf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

/** Initialize a JavaFX window and start it
 * @author Wide Factory Team
 */
public class App extends Application
{
    /** Main stage */
    @Getter
    @Setter
    private static Stage stage = new Stage();

    /**
     * Initialise a stage for JavaFX application
     * @param primaryStage the stage initialized by JavaFX
     * @throws IOException error raised when the file doesn't exist
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        // Set the static stage with stage initialized by JavaFX
        App.setStage(primaryStage);

        // Load fxml file and display it
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("view/fxml/hello-view.fxml")));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

}
