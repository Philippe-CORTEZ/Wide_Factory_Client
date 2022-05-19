package fr.univtln.wf.ws_clients;

import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;
import jakarta.websocket.Session;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data used and processed by the websocket client needed to displaying
 * Or to do the right actions
 * @author Wide Factory Team
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WSData
{
    /** To display a skeleton in real time */
    @Getter
    @Setter
    private static Skeleton skeleton = new Skeleton();

    /** To display a movement */
    @Getter
    @Setter
    private static Movement movement = new Movement();

    /** Describe state of the client */
    @Getter
    @Setter
    private static WSState state = WSState.STANDBY;

    /** Used ta send data from controllers */
    @Getter
    @Setter
    private static Session session;

    /** Used to know if the kinect is on */
    @Setter
    @Getter
    private static boolean kinectOn = false;
}