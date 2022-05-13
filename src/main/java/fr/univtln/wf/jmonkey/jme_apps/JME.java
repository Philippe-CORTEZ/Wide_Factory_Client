package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.MovementDisplayable;
import lombok.Getter;
import lombok.Setter;


/**
 * A Jmonkey application that enable to display a complete movement, used to display a reference movement
 * @author Wide Factory Team
 */
@Getter
@Setter
public class JME extends JMEGeneric
{
    /** A movement with skeletons that can be displayed */
    private MovementDisplayable mv;


    /** Default constructor */
    public JME()
    {
        this.mv = new MovementDisplayable();
    }


    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));
        // Display the first skeleton of the movement
        rootNode.attachChild(mv);

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
        mv.displayNextFrame(assetManager);
    }

}
