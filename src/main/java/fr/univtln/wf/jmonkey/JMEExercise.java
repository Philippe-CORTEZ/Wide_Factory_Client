package fr.univtln.wf.jmonkey;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import fr.univtln.wf.databases.daos.ExerciseDAO;
import lombok.Getter;

public class JMEExercise extends SimpleApplication
{
    /** A movement with skeletons that can be displayed */
    @Getter
    private ExerciseDisplayable exoDisplayable;


    /** Default constructor */
    public JMEExercise()
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
        exoDisplayable.setExercise(new ExerciseDAO().find("courrier"));

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
        exoDisplayable.displayNextFrame(assetManager);
    }

    public static void main(String[] args) {
        JMEExercise j = new JMEExercise();
        j.start();
    }
}
