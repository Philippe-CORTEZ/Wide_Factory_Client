package fr.univtln.wf;


import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

/**
 * The WebSocket client using Tyrus
 * @author Wide Factory Team
 */
@Slf4j
@ClientEndpoint
public class WSClient
{
    /**
     * Methode called when the client receive a websocket message
     * @param message the message received
     * @param session describe the websocket connection
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        log.info("msg " + message);
    }


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
            Session session = container.connectToServer(WSClient.class, URI.create("ws://localhost:9001/monTest"));
            session.getBasicRemote().sendText("Message from Tyrus");
        }
        catch (DeploymentException | IOException ex)
        {
            log.error("Error, cannot connect to the server ", ex);
        }

        while(true){}
    }

}
