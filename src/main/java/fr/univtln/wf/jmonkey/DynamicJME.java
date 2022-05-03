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

    private SkeletonDisplay skeletonDisplay;

    /**
     * init of Jmonkey display
     */
    @Override
    public void simpleInitApp()
    {
        cam.setLocation(new Vector3f(0,0, 3));
        // display the first skeleton of the movement
        rootNode.attachChild(skeletonDisplay);
    }


    public void updateSkeletonDisplay(Skeleton skeleton)
    {
        rootNode.detachAllChildren();
        skeletonDisplay = new SkeletonDisplay(skeleton, assetManager);
        rootNode.attachChild(skeletonDisplay);
    }

}
