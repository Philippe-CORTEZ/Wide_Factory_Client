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
public class Exercise implements MappingBidirectional
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PSEUDO_EDITOR")
    @Builder.Default
    private Person creator = new Person();

    /** Mapping many to many with movement */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @Builder.Default
    @JoinTable(name = "MOVEMENTS_EXERCISES", joinColumns = @JoinColumn(name = "NAME_EXERCISE"), inverseJoinColumns = @JoinColumn(name = "NAME_MOVEMENT"))
    private List<Movement> movements = new ArrayList<>();

    /**
     * add a movement to this exercise with his number of repetition
     * @param movement movement to add
     */
    public void addMovement(Movement movement)
    {
        movements.add(movement);
    }

    /**
     * setter which handle the mapping of skeletons
     * @param movements
     */
    public void setMovements(List<Movement> movements)
    {
        this.movements = movements;
    }

    /**
     * constructor without parameter
     */
    public Exercise() {
        name = "";
        description = "";
        movements = new ArrayList<>();
        persons = new ArrayList<>();
        creator = new Person();
    }

    /**
     * used when it needs to be persisted,
     * set the bidirectional relation
     */
    public void mappingAttribute()
    {
        for (Movement m : movements)
        {
            m.mappingAttribute();
        }
    }

    /**
     * mapping of skeletons
     */
    public void mappingSkeletons()
    {
        for (Movement m : movements)
        {
            m.mappingSkeletons();
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
