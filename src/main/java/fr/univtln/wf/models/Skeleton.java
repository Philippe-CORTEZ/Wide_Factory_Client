package fr.univtln.wf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * Represent the whole Skeleton of a person for one frame
 * @author Wide Factory Team
 */
@Slf4j

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@ToString
@EqualsAndHashCode(of = {"frame", "joints"})
@Getter
@Setter
public class Skeleton
{
    /** The frame number 1 is the first frame */
    @JsonProperty("frame")
    private int frame;
    /** List of join objects that represent 3D skeleton */
    @JsonProperty("joints")
    private List<Joint> joints;


    /**
     * Create a list of Skeleton objects with a JSON array format from string
     * The list can contain juste one skeleton or more
     * @param stringJSONArray the string formatted in JSON that represent the data of the skeletons
     * @return a list of Skeleton objects that correspond with the JSON array
     */
    public static List<Skeleton> newInstance(String stringJSONArray)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Skeleton> skeletons = null;

        try
        {
            skeletons = objectMapper.readValue(stringJSONArray, new TypeReference<>(){});
        }
        catch (JsonProcessingException error)
        {
            log.error("Error when parsing JSON for creating list of skeleton objects ", error);
        }
        return skeletons;
    }

}
