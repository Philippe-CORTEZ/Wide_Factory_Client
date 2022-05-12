package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import fr.univtln.wf.models.Movement;
import lombok.Getter;
import lombok.Setter;

/**
 * class that display a movement
 * @author Wide Factory Team
 */
@Getter
@Setter
public class MovementDisplayable extends Node
{
    /** movement to display */
    private Movement movement;
    /** number of the frame to display */
    private int count;
    /** Used to loop animation movement */
    private boolean loop;


    /** Default constructor that initialize a default movement */
    public MovementDisplayable()
    {
        this.movement = new Movement();
        this.count = 0;
        this.loop = true;
    }

    /**
     * Constructor that create a displayable movement with an object movement
     * @param movement movement object
     */
    public MovementDisplayable(Movement movement)
    {
        this.movement = movement;
        this.movement.mappingSkeletons();
        this.count = 0;
        this.loop = true;
    }


    /**
     * display the skeleton and put the count to the next frame
     * @param assetManager assetManager of Jmonkey application
     * @return boolean that indicate if the movement has been fully displayed
     */
    public boolean displayNextFrame(AssetManager assetManager)
    {
        // Animation not finished
        if (count < movement.getSkeletons().size())
        {
            this.detachAllChildren();
            SkeletonDisplayable sk = new SkeletonDisplayable();
            sk.displaySkeleton(movement.getSkeletons().get(count), assetManager);
            this.attachChild(sk);
            count++;
            return false;
        }
        // Animation finished and loop is activate, replay animation
        else if (loop)
        {
            count = 0;
        }
        return true;
    }

    /** Erase all data that was in before */
    public void clear()
    {
        movement.clear();
        detachAllChildren();
        count = 0;
    }

    /**
     * Special setter that change movement and reset the animation
     * @param movement movement object
     */
    public void setMovement(Movement movement)
    {
        this.movement = movement;
        this.movement.mappingSkeletons();
        this.count = 0;
    }

}
