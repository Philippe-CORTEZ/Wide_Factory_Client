package fr.univtln.wf.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Class that represent a movement
 * @author Wide Factory Team
 */
@Builder
@AllArgsConstructor

@Getter
@Setter

@Entity
public class Movement implements MappingBidirectional
{
    /** The movement name */
    @Id
    @Builder.Default
    private String name = "";

    /** List of skeletons that represent the movement */
    @OneToMany(mappedBy = "movement", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderBy("frame ASC")
    @Builder.Default
    private List<Skeleton> skeletons = new ArrayList<>();

    /** Description of a movement*/
    @Builder.Default
    private String description = "";

    /** Mapping many to many with exercise */
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "movements")
    @Builder.Default
    private List<Exercise> exercises = new ArrayList<>();

    /** default  repetition of the movement */
    @Column(name = "default_repetition")
    @Builder.Default
    private int defaultRepetition = 10;


    /** Default constructor initialize attributes with default value (not null) */
    public Movement()
    {
        this.name = "";
        this.skeletons = new ArrayList<>();
        this.description = "";
        this.exercises = new ArrayList<>();
        this.defaultRepetition = 10;
    }

    /**
     * Constructor
     * Convert un json file to an object movement
     * @param nameFileJson name of the json file
     * @param nameMovement name of the movement
     * @throws IOException during the opening of the JSON file
     */
    public Movement(String nameFileJson, String nameMovement) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        skeletons = objectMapper.readValue(new File(nameFileJson), new TypeReference<>(){});
        name = nameMovement;
        description = "";
        exercises = new ArrayList<>();
        defaultRepetition = 10;
    }


    /**
     * setter of skeletons and map them correctly
     * @param skeletons
     */
    public void setSkeleton(List<Skeleton> skeletons)
    {
        this.skeletons = skeletons;
    }

    /**
     * used when it needs to be persisted,
     * set the bidirectional relation
     */
    public void mappingAttribute()
    {
        for (Skeleton sk : skeletons)
        {
            sk.setMovement(this);
            sk.mappingAttribute();
        }
    }

    /**
     * mapping of skeletons
     */
    public void mappingSkeletons()
    {
        for(Skeleton skeleton : this.skeletons)
        {
            skeleton.mappingJoint();
        }
    }

    /**
     * clear all attribute of this movement
     */
    public void clear()
    {
        name = "";
        description = "";
        skeletons.clear();
        exercises.clear();
        defaultRepetition = 10;
    }


    /** Overriding equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;

        Movement movement = (Movement) o;

        return Objects.equals(name, movement.name);
    }

    /** Overriding hashCode */
    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

}
