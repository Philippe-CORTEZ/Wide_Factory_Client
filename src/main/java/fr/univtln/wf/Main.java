package fr.univtln.wf;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.WebSocketContainer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class Main
{
    /**
     * Launch the websocket client
     * @param args program arguments
     */
    public static void main(String[] args)
    {
        log.info("Client launched");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try
        {
            container.connectToServer(WSClient.class, URI.create("ws://localhost:9001/monTest"));
        }
        catch (DeploymentException | IOException ex)
        {
            log.error("Error, cannot connect to the server ", ex);
        }

        // Stand for stopping the program
        while(true){}
    }
}
