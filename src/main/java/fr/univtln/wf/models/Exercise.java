package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

/**
 * An exercise is a set of movements like squat --> push up
 * @author Wide Factory Team
 */
@AllArgsConstructor
@Builder

@Getter
@Setter

@Entity
public class Exercise
{
    /** The name of an exercise is unique */
    @Id
    @Builder.Default
    private String name = "";

    /** A short description of the exercise */
    @Builder.Default
    private String description = "";

    /** The coach that created this exercise */
    @ManyToOne
    @JoinColumn(name = "PSEUDO_EDITOR")
    @Builder.Default
    private Person creator = new Person();

    /** Mapping many to many with movement */
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.PERSIST)
    @OrderBy("position ASC")
    @Builder.Default
    private List<FragmentExercise> fragments = new ArrayList<>();



    /** Constructor without parameter */
    public Exercise()
    {
        name = "";
        description = "";
        fragments = new ArrayList<>();
        creator = new Person();
    }


    /**
     * add a movement to this exercise with his number of repetition
     * @param movement movement to add
     */
    public void addMovement(Movement movement)
    {
        FragmentExercise fragment = FragmentExercise
                .builder()
                .position(fragments.size())
                .exercise(this)
                .movement(movement)
                .build();

        fragments.add(fragment);
    }

    /**
     * add a movement to this exercise with his number of repetition
     * @param movement movement to add
     * @param repetition number of repetition of this movement
     */
    public void addMovement(Movement movement, int repetition)
    {
        addMovement(movement);
        fragments.get(fragments.size() - 1).setRepetition(repetition);
    }


    /** Mapping of skeletons used to make a link between exercise movements and skeletons */
    public void mappingSkeletons()
    {
        // Currently this method is not used
        // The mapping for exercise and movements is performed in the setter of ExerciseDisplayable
        // (With constructor parameters of Movement)
        for (FragmentExercise fragment : fragments)
        {
            fragment.getMovement().mappingSkeletons();
        }
    }

    /** Overriding equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;

        Exercise exercise = (Exercise) o;

        return Objects.equals(name, exercise.name);
    }

    /** Overriding hashCode */
    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

    /** Overriding toString */
    @Override
    public String toString()
    {
        return name;
    }

}
