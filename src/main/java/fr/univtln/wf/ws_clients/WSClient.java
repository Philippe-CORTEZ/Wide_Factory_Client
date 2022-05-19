package fr.univtln.wf.ws_clients;


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
        // Probably TURN ON the kinect here
    }


    /**
     * Methode called when the client receive a websocket message
     * @param message the message received
     * @param session describe the websocket connection
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        if (message.startsWith("ok"))
        {
            WSData.setKinectOn(!WSData.isKinectOn());
        }

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

            else if (WSData.getState().equals(WSState.REAL_TIME))
            {
                // Set the skeleton displayable in the Jmonkey app to make an animation
                // If it's a continuous displaying, in the list there is only one skeleton
                WSData.setSkeleton(skeletons.get(0));
                skeletons.clear();
            }
        }
    }

    /**
     * Used to send message to the server, useful to command kinect from client
     * @param message EnumMessage action to perform on the Kinect
     */
    public static void sendMessage(EnumMessage message)
    {
        try
        {
            WSData.getSession().getBasicRemote().sendText(String.valueOf(message.ordinal()));
        } catch (IOException error)
        {
           log.error("Unable to send message to the server", error);
        }
    }

}
