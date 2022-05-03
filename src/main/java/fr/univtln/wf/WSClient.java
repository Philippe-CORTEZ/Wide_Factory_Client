package fr.univtln.wf;


import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * The WebSocket client using Tyrus
 * @author Wide Factory Team
 */
@Slf4j
@ClientEndpoint
public class WSClient
{
    /**
     * Methode called when the client open a websocket connection with a server
     * @param session describe the websocket connection
     */
    @OnOpen
    public void onOpen(Session session)
    {
        try
        {
            // For the moment send just a message to communicate with the server
            session.getBasicRemote().sendText("start");
        }
        catch (IOException error)
        {
            log.error("Error in onOpen : ", error);
        }
    }


    /**
     * Methode called when the client receive a websocket message
     * @param message the message received
     * @param session describe the websocket connection
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        log.info("msg " + message);

        // Transform the string message (JSON formated) into Skeleton list
        List<Skeleton> skeletons = Skeleton.newInstance(message);

        // Display the movement
        Main t = new Main();
        Main.movement.setName("Test");
        Main.movement.setSkeletons(skeletons);
        t.start();
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
        }
        catch (DeploymentException | IOException ex)
        {
            log.error("Error, cannot connect to the server ", ex);
        }

        while(true){}
    }

}
