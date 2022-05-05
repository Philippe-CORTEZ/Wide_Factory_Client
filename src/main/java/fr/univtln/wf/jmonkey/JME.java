package fr.univtln.wf.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import fr.univtln.wf.models.Movement;
import lombok.Getter;
import lombok.Setter;


/**
 * A Jmonkey application that enable to display a complete movement, used to display a reference movement
 * @author Wide Factory Team
 */
@Getter
@Setter
public class JME extends SimpleApplication
{
    /** A set of skeleton objects that represent the whole movement */
    private Movement movement;
    /** Jmonkey structure for representing the skeleton in 3D environment */
    private SkeletonDisplayable sk;
    /** Count the frame of the movement to reset the movement animation when it finish */
    private int count;


    /** Default constructor */
    public JME()
    {
        this.movement = new Movement();
        this.sk = new SkeletonDisplayable();
        this.count = 0;
    }


    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));
        // Display the first skeleton of the movement
        rootNode.attachChild(sk);

        // Set frame rate to 30 fps to synchronize with kinect
        settings.setFrameRate(30);
    }

    /**
     * Update the Jmonkey components when is possible
     * @param tpf float that represent the time elapsed during one frame
     */
    @Override
    public void simpleUpdate(float tpf)
    {
        if (count < movement.getSkeletons().size())
        {
            // refresh the skeleton with the following one
            sk.displaySkeleton(movement.getSkeletons().get(count), assetManager);
            count++;
        }
        // restart the animation
        else count = 0;
    }

}
