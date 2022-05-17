package fr.univtln.wf;

import fr.univtln.wf.ws_clients.WSClient;
import fr.univtln.wf.ws_clients.WSData;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.WebSocketContainer;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

/** Initialize JavaFX and WS client app
 * @author Wide Factory Team
 */
@Slf4j
public class Launcher
{
    public static void main(String[] args)
    {
        // Launch the WS client
        log.info("Client launched");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try
        {
            // Try to connect on port 9001
            WSData.setSession(container.connectToServer(WSClient.class, URI.create("ws://localhost:9001/monTest")));
        }
        catch (DeploymentException | IOException ex)
        {
            log.error("Error, cannot connect to the server ", ex);
        }

        // Launch the JavaFX app
        Application.launch(App.class);
    }

}