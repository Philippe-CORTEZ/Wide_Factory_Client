package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Class only used to represent the Many To Many relation between Exercise and Movement with extra column
 * @author Wide Factory Team
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter

@Entity
@Table(name = "MOVEMENTS_EXERCISES", uniqueConstraints = { @UniqueConstraint(columnNames = {"NAME_MOVEMENT", "NAME_EXERCISE" }) })
public class MovementsExercises
{
    /** Id auto generated by the database */
    @Id
    private long id;

    /** The movement mapped with his exercise */
    @ManyToOne
    @JoinColumn(name = "NAME_MOVEMENT")
    private Movement movement;

    /** The exercise mapped with his movement */
    @ManyToOne
    @JoinColumn(name = "NAME_EXERCISE")
    private Exercise exercise;

    /** Default repetition for one movement and one exercise */
    @Column(name = "DEFAULT_REPETITION")
    private int defaultRepetition;

}