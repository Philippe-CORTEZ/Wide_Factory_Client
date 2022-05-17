package fr.univtln.wf.ws_clients;

import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;
import jakarta.websocket.Session;
import lombok.Getter;
import lombok.Setter;

public class WSData {
    @Getter
    @Setter
    private static Skeleton skeleton = new Skeleton();

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

}