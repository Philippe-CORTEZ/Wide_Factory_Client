package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.math.Vector3f;
import fr.univtln.wf.jmonkey.ExerciseDisplayable;
import lombok.Getter;

/**
 * A Jmonkey application that enable to display a complete exercise
 * @author Wide Factory Team
 */
public class JMEVisualizeExercise extends JMEGeneric
{
    /** A movement with skeletons that can be displayed */
    @Getter
    private final ExerciseDisplayable exoDisplayable;

    /** Default constructor */
    public JMEVisualizeExercise()
    {
        this.exoDisplayable = new ExerciseDisplayable();
    }


    /** Initialize the Jmonkey components */
    @Override
    public void simpleInitApp()
    {
        // Move camera rearward
        cam.setLocation(new Vector3f(0,0, 3));
        // Display the first skeleton of the movement
        rootNode.attachChild(exoDisplayable);
    }

    /**
     * Update the Jmonkey components when is possible
     * @param tpf float that represent the time elapsed during one frame
     */
    @Override
    public void simpleUpdate(float tpf)
    {
        exoDisplayable.displayNextFrame(assetManager);
    }

}
