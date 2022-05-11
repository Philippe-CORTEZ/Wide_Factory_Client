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

    /** The people that done this exercise */
    @ManyToMany(mappedBy = "exercices", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Person> persons = new ArrayList<>();

    /** The coach that created this exercise */
    @ManyToOne
    @JoinColumn(name = "PSEUDO_EDITOR")
    private Person creator;

    /** Mapping many to many with movement */
    @OneToMany(mappedBy = "movement")
    @Builder.Default
    private Set<MovementsExercises> movements = new HashSet<>();

    /**
     * add a movement to this exercise with his number of repetition
     * @param movement movement to add
     * @param repetition number of repetition
     */
    public void addMovement(Movement movement, int repetition)
    {
        MovementsExercises movementsExercises = MovementsExercises.builder()
                .exercise(this)
                .movement(movement)
                .defaultRepetition(repetition)
                .build();
        movements.add(movementsExercises);
    }

    /**
     * constructor without parameter
     */
    public Exercise() {
        name = "";
        description = "";
        movements = new HashSet<>();
        persons = new ArrayList<>();
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
