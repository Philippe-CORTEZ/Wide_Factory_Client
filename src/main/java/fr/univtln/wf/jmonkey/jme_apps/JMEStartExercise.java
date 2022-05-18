package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.ExerciseDisplayable;
import fr.univtln.wf.jmonkey.SkeletonDisplayable;
import fr.univtln.wf.ws_clients.WSData;
import lombok.Getter;
import lombok.Setter;

/**
 * JME which manage the display of an exercise started
 * @author Wide Factory Team
 */
@Getter
@Setter
public class JMEStartExercise extends JMEGeneric
{
    /** Used to display the reference exercise recorded by a coach */
    private ExerciseDisplayable exerciseDisplayable;
    /** Used to display user's skeleton in real time */
    private SkeletonDisplayable skeletonDisplayable;


    /** Default constructor */
    public JMEStartExercise()
    {
        exerciseDisplayable = new ExerciseDisplayable();
        skeletonDisplayable = new SkeletonDisplayable();
    }


    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera backward
        cam.setLocation(new Vector3f(0,0, 3));

        // Attach the two skeletons to display (reference and user)
        rootNode.attachChild(exerciseDisplayable);
        rootNode.attachChild(skeletonDisplayable);

        exerciseDisplayable.move(-1, 0, 0);
    }

    /**
     * Update the Jmonkey components when is possible
     * @param tpf float that represent the time elapsed during one frame
     */
    @Override
    public void simpleUpdate(float tpf)
    {
        exerciseDisplayable.displayNextFrame(assetManager);
        // Change skeleton with the data get by kinect and stored in WSData (for real time)
        skeletonDisplayable.displaySkeleton(WSData.getSkeleton(), assetManager);
    }

}
