package fr.univtln.wf.ws_clients;

import fr.univtln.wf.jmonkey.jme_apps.DynamicJME;
import fr.univtln.wf.jmonkey.jme_apps.JMEVisualizeMovement;
import lombok.Getter;

public class WSDataJME {
    /** Instance of Jmonkey app to display skeletons in real time */
    @Getter
    private static final DynamicJME DYNAMIC_JME = new DynamicJME();

    /** Instance of Jmonkey app to display skeletons of a movement (not real time) */
    @Getter
    private static final JMEVisualizeMovement STATIC_JME_MOVEMENT = new JMEVisualizeMovement();
}
