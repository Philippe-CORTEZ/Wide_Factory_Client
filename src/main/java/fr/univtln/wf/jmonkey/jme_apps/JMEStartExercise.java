package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.ExerciseDisplayable;
import fr.univtln.wf.jmonkey.SkeletonDisplayable;
import fr.univtln.wf.models.Skeleton;
import lombok.Getter;
import lombok.Setter;

/**
 * Jmonkey app called when a exercise start
 * @author Wide Factory Team
 */
@Getter
public class JMEStartExercise extends JMEGeneric
{
    /** Used to display all referenced movements recorded by a coach */
    private final ExerciseDisplayable exerciseDisplayable;

    /** Used to display in real time skeleton of user */
    private final SkeletonDisplayable skeletonDisplayable;
    @Setter
    private Skeleton skeleton;


    /** Default constructor initialize attributes by its default values */
    public JMEStartExercise()
    {
        this.exerciseDisplayable = new ExerciseDisplayable();
        this.skeletonDisplayable = new SkeletonDisplayable();
        this.skeleton = new Skeleton();
    }

    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));

        // Display the skeletons of the referenced exercise and the user's skeleton
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
        // Update the referenced exercise
        exerciseDisplayable.displayNextFrame(assetManager);
        // Update the real time user's skeleton
        skeletonDisplayable.displaySkeleton(skeleton, assetManager);
    }

}
