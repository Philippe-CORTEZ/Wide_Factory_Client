package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Builder

@Getter
@Setter

@Entity
public class Person {
    @Id
    private String pseudo;

    private String lastname;
    private String firstname;
    private RoleEnum roleEnum;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PERSONS_EXERCICES",
            joinColumns = @JoinColumn(name = "PSEUDO"),
            inverseJoinColumns = @JoinColumn(name = "NOM_EXERCICE"))
    private List<Exercice> exercices;
}
