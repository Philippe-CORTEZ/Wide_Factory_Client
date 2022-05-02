package fr.univtln.wf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class Skeleton
{
    /** The frame number 1 is the first frame */
    @JsonProperty("frame")
    private int frame;
    /** List of join objects that represent 3D skeleton */
    @JsonProperty("joints")
    private List<Joint> joints;


    /**
     * Create a Skeleton object with a JSON format in string
     * @param stringJSON the string formated in JSON that represent the data of one skeleton
     * @return a Skeleton object that correspond with the JSON
     */
    public static Skeleton newInstance(String stringJSON)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Skeleton skeleton = null;

        try
        {
            skeleton = objectMapper.readValue(stringJSON, Skeleton.class);
        }
        catch (JsonProcessingException error)
        {
            log.error("Error when parsing JSON for creating skeleton object ", error);
        }
        return skeleton;
    }

}
