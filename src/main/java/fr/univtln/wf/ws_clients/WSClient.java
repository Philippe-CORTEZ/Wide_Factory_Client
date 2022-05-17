package fr.univtln.wf.ws_clients;


import fr.univtln.wf.jmonkey.jme_apps.DynamicJME;
import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeMovement;
import fr.univtln.wf.models.Skeleton;
import jakarta.websocket.*;
import lombok.Getter;
import lombok.Setter;
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

        // Transform the string message (JSON formatted) into Skeleton list (one or more skeleton)
        List<Skeleton> skeletons = Skeleton.newInstance(message);


        if(!skeletons.isEmpty())
        {
            // When recording, skeletons are send in one block
            if(WSData.getState().equals(WSState.RECORDING))
            {
                // Just add all skeletons in the final movement in JME class
                WSData.getMovement().setSkeletons(skeletons);
            }

            else
            {
                log.info("");
            }

            // Set the skeleton displayable in the Jmonkey app to make an animation
            // If it's a continuous displaying, in the list there is only one skeleton
            WSData.setSkeleton(skeletons.get(0));
        }
    }

}
