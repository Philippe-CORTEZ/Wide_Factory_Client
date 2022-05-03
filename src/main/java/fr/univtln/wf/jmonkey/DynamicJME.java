package fr.univtln.wf.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import fr.univtln.wf.models.Skeleton;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DynamicJME extends SimpleApplication
{

    private SkeletonDisplayable sk = new SkeletonDisplayable();

    /**
     * init of Jmonkey display
     */
    @Override
    public void simpleInitApp()
    {
        cam.setLocation(new Vector3f(0,0, 3));
        // display the first skeleton of the movement
        rootNode.attachChild(sk);
    }


    public void updateSkeletonDisplay(Skeleton skeleton)
    {
//        rootNode.detachAllChildren();
        sk.displaySkeleton(skeleton, assetManager);
//        rootNode.attachChild(skeletonDisplay);
    }

}
