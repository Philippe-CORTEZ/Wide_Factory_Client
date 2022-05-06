package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * An exercise is a set of movements like squat --> push up
 * @author Wide Factory Team
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter

@Entity
public class Exercise
{
    /** The name of an exercise is unique */
    @Id
    private String name;
    /** A short description of the exercise */
    private String description;

    /** The people that done this exercise */
    @ManyToMany(mappedBy = "exercices", cascade = CascadeType.PERSIST)
    private List<Person> persons;

    /** The coach that created this exercise */
    @ManyToOne
    @JoinColumn(name = "PSEUDO_EDITOR")
    private Person creator;

    /** Mapping many to many with movement */
    @OneToMany(mappedBy = "exercise")
    private Set<MovementsExercises> exercises;


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
        return "Exercise{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
