package fr.univtln.wf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * class that represent a movement
 * @author Wide Factory Team
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Movement
{
    /** The movement name */
    private String name;
    /** List of skeletons that represent the movement */
    private List<Skeleton> skeletons;

    /**
     * constructor
     * convert un json file to an object movement
     * @param nameFileJson name of the json file
     * @param nameMovement name of the movement
     * @throws IOException during the opening of the JSON file
     */
    public Movement(String nameFileJson, String nameMovement) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        skeletons = objectMapper.readValue(new File(nameFileJson), new TypeReference<List<Skeleton>>(){});
        name = nameMovement;
    }

    /**
     * Constructor
     * @param skeletons list of Skeletons
     * @param name the movement name
     */
    public Movement(List<Skeleton> skeletons, String name)
    {
        this.name = name;
        this.skeletons = skeletons;
    }

}
