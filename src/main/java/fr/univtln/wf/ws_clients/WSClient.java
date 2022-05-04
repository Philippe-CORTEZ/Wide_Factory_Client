package fr.univtln.wf.ws_clients;


import com.jme3.system.AppSettings;
import fr.univtln.wf.jmonkey.DynamicJME;
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
    /** Instance of Jmonkey app to display skeletons */
    private final DynamicJME dynamicJME = new DynamicJME();


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

        // Create the Jmonkey app for visualisation and set the window
        dynamicJME.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "My awesome Game");
        settings.put("VSync", true);
        settings.put("Samples", 4);
        dynamicJME.setSettings(settings);
        dynamicJME.start();
    }


    /**
     * Methode called when the client receive a websocket message
     * @param message the message received
     * @param session describe the websocket connection
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        // Currently it display only skeletons there isn't processing before to check another message
        log.info("msg " + message);

        // Transform the string message (JSON formatted) into Skeleton list (one or more skeleton)
        List<Skeleton> skeletons = Skeleton.newInstance(message);


        if(!skeletons.isEmpty())
        {
            // Set the skeleton displayable in the Jmonkey app to make an animation
            // If it's a continuous displaying, in the list there is only one skeleton
            dynamicJME.setSkeleton(skeletons.get(0));
        }
    }

}
