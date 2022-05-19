package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
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
     * @param exercise an exercise given
     */
    public ExerciseDisplayable(Exercise exercise)
    {
       this.exercise = exercise;
       for (FragmentExercise fragment : exercise.getFragments())
       {
           // Add a movement with parameters constructor (mappingSkeleton is called)
           movementDisplayables.add(new MovementDisplayable(fragment.getMovement()));
       }
       if (!movementDisplayables.isEmpty()) attachChild(movementDisplayables.get(0));
       this.exercise.mappingSkeletons();
    }

    /**
     * display the next frame of the exercise
     */
    public void displayNextFrame(AssetManager assetManager)
    {
        boolean movementFinished;
        if (movementNumber < movementDisplayables.size())
        {
            if (movementRepetition < exercise.getFragments().get(movementNumber).getRepetition())
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
        }
    }

    /**
     * Special setter that reset exercise displayable in same time
     * @param exercise new exercise given
     */
    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
        movementDisplayables.clear();
        for (FragmentExercise fragment : exercise.getFragments())
        {
            // Add a movement with parameters constructor (mappingSkeleton is called)
            movementDisplayables.add(new MovementDisplayable(fragment.getMovement()));
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
