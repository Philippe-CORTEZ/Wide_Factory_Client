package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.ExerciseDisplayable;
import fr.univtln.wf.jmonkey.SkeletonDisplayable;
import fr.univtln.wf.ws_clients.WSData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JMEStartExercise extends JMEGeneric{
    private ExerciseDisplayable exerciseDisplayable;
    private SkeletonDisplayable skeletonDisplayable;

    public JMEStartExercise()
    {
        exerciseDisplayable = new ExerciseDisplayable();
        skeletonDisplayable = new SkeletonDisplayable();
    }

    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));
        // Display the first skeleton of the movement
        rootNode.attachChild(exerciseDisplayable);
        rootNode.attachChild(skeletonDisplayable);
    }

    /**
     * Update the Jmonkey components when is possible
     * @param tpf float that represent the time elapsed during one frame
     */
    @Override
    public void simpleUpdate(float tpf)
    {
        exerciseDisplayable.displayNextFrame(assetManager);
        skeletonDisplayable.displaySkeleton(WSData.getSkeleton(), assetManager);

    }
}