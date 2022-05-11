package fr.univtln.wf.ws_clients;


import com.jme3.system.AppSettings;
import fr.univtln.wf.jmonkey.DynamicJME;
import fr.univtln.wf.jmonkey.JME;
import fr.univtln.wf.models.Joint;
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
    /** Used ta send data from controllers */
    @Getter
    @Setter
    private static Session session;


    /** Instance of Jmonkey app to display skeletons in real time */
    @Getter
    private static final DynamicJME DYNAMIC_JME = new DynamicJME();

    /** Instance of Jmonkey app to display skeletons of a movement (not real time) */
    @Getter
    private static final JME STATIC_JME = new JME();

    /** Describe state of the client */
    @Getter
    @Setter
    private static WSState state = WSState.STANDBY;


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

        // Initialize Jmonkey apps for visualisation dynamic and non dynamic
        DYNAMIC_JME.setShowSettings(false);
        STATIC_JME.setShowSettings(false);

        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "My awesome Game");
        settings.put("VSync", true);
        settings.put("Samples", 4);

        DYNAMIC_JME.setSettings(settings);
        STATIC_JME.setSettings(settings);
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
            if(state.equals(WSState.RECORDING))
            {
                // Just add all skeletons in the final movement in JME class
                for(Skeleton sk : skeletons)
                {
                    sk.setMovement(STATIC_JME.getMv().getMovement());
                    for (Joint j: sk.getJoints())
                    {
                        j.setSkeleton(sk);
                    }
                }
                STATIC_JME.getMv().getMovement().getSkeletons().addAll(skeletons);
            }

            else
            {
                log.info("");
            }

            // Set the skeleton displayable in the Jmonkey app to make an animation
            // If it's a continuous displaying, in the list there is only one skeleton
//            DYNAMIC_JME.setSkeleton(skeletons.get(0));
        }
    }

}
