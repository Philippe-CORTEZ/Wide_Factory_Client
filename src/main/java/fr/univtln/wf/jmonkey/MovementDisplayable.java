package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import fr.univtln.wf.models.Movement;
import lombok.Getter;
import lombok.NoArgsConstructor;
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


    /** Default constructor that initialize a default movement */
    public MovementDisplayable()
    {
        this.movement = new Movement();
    }


    /** number of the frame to display */
    private int count = 0;

    private boolean loop = true;

    /** display the skeleton and put the count to the next frame */
    public void displayNextFrame(AssetManager assetManager)
    {
        if (count < movement.getSkeletons().size())
        {
            this.detachAllChildren();
            SkeletonDisplayable sk = new SkeletonDisplayable();
            sk.displaySkeleton(movement.getSkeletons().get(count), assetManager);
            this.attachChild(sk);
            count++;
        }
        else if (loop)
        {
            count = 0;
        }
    }

    public MovementDisplayable(Movement movement)
    {
        this.movement = movement;
    }

    public void clear()
    {
        movement.clear();
        detachAllChildren();
        count = 0;
    }

    public void setMovement(Movement movement)
    {
        this.movement = movement;
        this.count = 0;
    }

}
