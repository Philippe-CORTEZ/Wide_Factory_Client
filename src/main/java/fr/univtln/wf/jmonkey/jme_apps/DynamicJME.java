package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.SkeletonDisplayable;
import fr.univtln.wf.models.Skeleton;
import lombok.Getter;
import lombok.Setter;

/**
 * A Jmonkey application that enable to display a continus skeleton recorded by the Kinect
 * @author Wide Factory Team
 */
@Getter
@Setter
public class DynamicJME extends JMEGeneric
{
    /** Jmonkey structure for representing the skeleton in 3D environment */
    private SkeletonDisplayable sk;
    /** The skeleton get by the WS client, used to update the SkeletonDisplayable */
    private Skeleton skeleton;


    /** Default constructor */
    public DynamicJME()
    {
        this.sk = new SkeletonDisplayable();
        this.skeleton = new Skeleton();
    }


    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));
        // Display the first skeleton
        rootNode.attachChild(sk);
    }


    /**
     * Update the Jmonkey components when is possible
     * @param tpf float that represent the time elapsed during one frame
     */
    @Override
    public void simpleUpdate(float tpf)
    {
        sk.displaySkeleton(skeleton, assetManager);
    }

}
