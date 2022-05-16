package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

/**
 * Entity that enable to make a many to many link between Exercise and Movement with extra columns
 * @author Wide Factory Team
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter

@Entity
@Table(name = "FRAGMENTS_EXERCISES")
public class FragmentExercise
{
    /** Id auto generated by the database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** An exercise that refers to */
    @ManyToOne
    @JoinColumn(name = "NAME_EXERCISE")
    private Exercise exercise;

    /** A movement which is a part of an exercise */
    @ManyToOne
    @JoinColumn(name = "NAME_MOVEMENT")
    private Movement movement;


    public void setName(String name) {
        this.name = this.movement.getName();
    }

    private String name;





    /** Position of the movement in the exercise */
    private int position;

    /** Repetition of the movement in the exercise */
    @Column(name = "REPETITION")
    @Builder.Default
    private int repetition = 5;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof FragmentExercise)) return false;

        FragmentExercise that = (FragmentExercise) o;

        if (position != that.position) return false;
        if (!Objects.equals(exercise, that.exercise)) return false;
        return Objects.equals(movement, that.movement);
    }

    @Override
    public int hashCode() {
        int result = exercise != null ? exercise.hashCode() : 0;
        result = 31 * result + (movement != null ? movement.hashCode() : 0);
        result = 31 * result + position;
        return result;
    }
}
