package fr.univtln.wf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** Initialize a JavaFX window and start it
 * @author Wide Factory Team
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("view/fxml/hello-view.fxml")));
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
