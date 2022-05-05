package fr.univtln.wf.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Class that represent a movement
 * @author Wide Factory Team
 */
@Builder
@NoArgsConstructor
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
    @OneToMany(mappedBy = "movement")
    private List<Skeleton> skeletons;

    /** Description of a movement*/
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return name != null && Objects.equals(name, movement.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
