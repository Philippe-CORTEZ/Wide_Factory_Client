package fr.univtln.wf.ws_clients;


import fr.univtln.wf.jmonkey.JME;
import fr.univtln.wf.models.Skeleton;
import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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
            // For the moment it send just a message to communicate with the server
            session.getBasicRemote().sendText("start");
        }
        catch (IOException error)
        {
            log.error("Error in onOpen : ", error);
        }

        // Create the Jmonkey app for visualisation
        JME jme = new JME();
        jme.start();
    }


    /**
     * Methode called when the client receive a websocket message
     * @param message the message received
     * @param session describe the websocket connection
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        // Currently it display only skeleton there isn't processing before
        log.info("msg " + message);

        // Transform the string message (JSON formated) into Skeleton list
        List<Skeleton> skeletons = Skeleton.newInstance(message);

        // Display the movement
        JME.movement.setName("Test");
        JME.movement.setSkeletons(skeletons);
    }
}
