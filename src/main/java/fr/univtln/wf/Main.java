package fr.univtln.wf;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import lombok.SneakyThrows;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main extends SimpleApplication {

    private static Movement movement;
    private static SkeletonDisplayable sk = new SkeletonDisplayable();
    private static int count = 0;
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Main t = new Main();
        movement = new Movement("src/main/resources/movement_coucou.json", "Test"); // json to read and convert
        t.start();
    }


    @Override
    /**
     * init of Jmonkey display
     */
    public void simpleInitApp(){
        cam.setLocation(new Vector3f(0,0, 3));
        // display the first skeleton of the movement
        rootNode.attachChild(sk);
    }

    @SneakyThrows
    @Override
    public void simpleUpdate(float tpf) {
//        sleep(1000); // to slow down the frame rate
        if (count < movement.getSkeletons().size())
        {
            sk.displaySkeleton(movement.getSkeletons().get(count), assetManager); // refresh the skeleton with the following one
            count++;
        }
        else count = 0; // restart the animation to the start
    }
}