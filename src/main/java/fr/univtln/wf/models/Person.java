package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * Describe a person like a user or a coach
 * @author Wide Factory Team
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Getter
@Setter

@Entity
public class Person
{
    /** The pseudo of the user, unique of each person */
    @Id
    private String pseudo;

    /** last name and first name */
    private String lastname;
    private String firstname;
    /** role of the person, simple user or coach at this time */
    private RoleEnum roleEnum;

    /** List of exercises that represented the exercises already done by the person */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PERSONS_EXERCISES", joinColumns = @JoinColumn(name = "PSEUDO"), inverseJoinColumns = @JoinColumn(name = "NAME_EXERCISE"))
    private List<Exercise> exercices;


    /** Overriding equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return Objects.equals(pseudo, person.pseudo);
    }

    /** Overriding hashCode */
    @Override
    public int hashCode()
    {
        return pseudo != null ? pseudo.hashCode() : 0;
    }

    /** Overriding toString */
    @Override
    public String toString()
    {
        return "Person{" +
                "pseudo='" + pseudo + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", roleEnum=" + roleEnum +
                ", exercices=" + exercices +
                '}';
    }

}
