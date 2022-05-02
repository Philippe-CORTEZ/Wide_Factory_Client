package fr.univtln.wf;


import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

@Slf4j
@ClientEndpoint
public class TestClientWebSocketTyrus
{
    @OnMessage
    public void onMessage(String message, Session session)
    {
        log.info("msg " + message);
    }

    public static void main(String[] args)
    {
        log.info("Lancement client");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            Session session = container.connectToServer(TestClientWebSocketTyrus.class, URI.create("ws://localhost:9001/monTest"));
            session.getBasicRemote().sendText("message depuis Tyrus");
        } catch (DeploymentException | IOException ex)
        {
            log.error("Impossible de se connecter au serveur", ex);
        }
    }

}
