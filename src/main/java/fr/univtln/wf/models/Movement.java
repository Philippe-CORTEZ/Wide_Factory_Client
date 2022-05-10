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
public class Movement
{
    /** The movement name */
    @Id
    private String name;

    /** List of skeletons that represent the movement */
    @OneToMany(mappedBy = "movement", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderBy("frame ASC")
    private List<Skeleton> skeletons;

    /** Description of a movement*/
    private String description;

    /** Mapping many to many with exercise */
    @OneToMany(mappedBy = "movement")
    Set<MovementsExercises> movements;

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
        movements = new HashSet<>();

        // To bind skeleton to movement and joint to skeleton (for JPA)
        for(Skeleton skeleton : skeletons)
        {
            skeleton.setMovement(this);

            for(Joint joint : skeleton.getJoints())
            {
                joint.setSkeleton(skeleton);
            }
        }
    }

    public Movement() {
        name = "";
        skeletons = new ArrayList<>();
        description = "";
        movements = new HashSet<>();
    }

    /**
     * Constructor with a list of skeletons
     * @param skeletons list of Skeletons
     * @param name the movement name
     */
    public Movement(List<Skeleton> skeletons, String name)
    {
        this.name = name;
        this.skeletons = skeletons;
    }

    /**
     * clear all attribute of this movement
     */
    public void clear()
    {
        name = "";
        description = "";
        skeletons.clear();
        movements.clear();
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
