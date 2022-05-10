package fr.univtln.wf;

import fr.univtln.wf.ws_clients.WSClient;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.WebSocketContainer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

// TODO : Suppress this useless class

/**
 * This is the main class of client app, it launch the WS client
 * And communicate with the WS server to process the Kinect data
 * @author Wide Factory Team
 */
@Slf4j
public class Main
{
    /**
     * Launch the websocket client and try to connect to a server
     * @param args program arguments
     */
    public static void main(String[] args)
    {
        log.info("Client launched");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try
        {
            // Try to connect on port 9001
            WSClient.setSession(container.connectToServer(WSClient.class, URI.create("ws://localhost:9001/monTest")));
        }
        catch (DeploymentException | IOException ex)
        {
            log.error("Error, cannot connect to the server ", ex);
        }

        // Waiting for stop the program
        while(true){}
    }
}
