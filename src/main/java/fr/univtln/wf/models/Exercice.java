package fr.univtln.wf.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Exercice {
    @Id
    private String name;
    private String description;

    @ManyToMany(mappedBy = "exercices", cascade = CascadeType.PERSIST)
    List<Person> persons;
}
