package fr.univtln.wf.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import fr.univtln.wf.models.Movement;
import lombok.SneakyThrows;


import static java.lang.Thread.sleep;

public class JME extends SimpleApplication {

    public static Movement movement = new Movement();
    private static SkeletonDisplay sk;
    private static int count = 0;


    /**
     * init of Jmonkey display
     */
    @Override
    public void simpleInitApp()
    {
        cam.setLocation(new Vector3f(0,0, 3));
        // display the first skeleton of the movement
        sk = new SkeletonDisplay(movement.getSkeletons().get(0), assetManager);
        rootNode.attachChild(sk);
    }

    @SneakyThrows
    @Override
    public void simpleUpdate(float tpf) {
        sleep(33); // to slow down the frame rate
        if (count < movement.getSkeletons().size())
        {
            sk.refreshJoints(movement.getSkeletons().get(count)); // refresh the skeleton with the following one
            count++;
        }
        else count = 0; // restart the animation to the start
    }
}