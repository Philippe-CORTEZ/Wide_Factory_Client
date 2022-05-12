package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.Movement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * class that can display an exercise
 */
public class ExerciseDisplayable extends Node {
    @Getter
    private Exercise exercise;

    private List<MovementDisplayable> movementDisplayables;

    private int movementNumber;

    private int movementRepetition;

    /**
     * constructor without parameter
     */
    public ExerciseDisplayable() {
        exercise = new Exercise();
        movementDisplayables = new ArrayList<>();
        movementNumber = 0;
    }

    /**
     * constructor with an exercise
     * @param exercise
     */
    public ExerciseDisplayable(Exercise exercise)
    {
       this.exercise = exercise;
       for (Movement m : exercise.getMovements())
       {
           movementDisplayables.add(new MovementDisplayable(m));
       }
       if (!movementDisplayables.isEmpty()) attachChild(movementDisplayables.get(0));
       this.exercise.mappingSkeletons();
    }

    /**
     * dispaly the next frame of the exercise
     * @return boolean that indicate if the exercise has been fully displayed
     */
    public boolean displayNextFrame(AssetManager assetManager)
    {
        boolean movementFinished;
        if (movementNumber < movementDisplayables.size())
        {
            if (movementRepetition < exercise.getMovements().get(movementNumber).getDefaultRepetition())
            {
                movementFinished = movementDisplayables.get(movementNumber).displayNextFrame(assetManager);
                if (movementFinished)
                {
                    movementRepetition++;
                }
            }
            else
            {
                attachNextMovement();
            }
            return false;
        }
        return true;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
        movementDisplayables.clear();
        for (Movement m : exercise.getMovements())
        {
            movementDisplayables.add(new MovementDisplayable(m));
        }
        if (!movementDisplayables.isEmpty()) attachChild(movementDisplayables.get(0));
        this.movementNumber = 0;
        this.movementRepetition = 0;
    }

    /**
     * detach all children of this to attach the next movement if possible
     */
    private void attachNextMovement()
    {
        detachAllChildren();
        movementNumber++;
        if (movementNumber < movementDisplayables.size())
        {
            attachChild(movementDisplayables.get(movementNumber));
        }
        movementRepetition = 0;
    }
}
