package fr.utln;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Movement {
    private String name;
    private List<Skeleton> skeletons;

    public Movement(String nameFileJson, String nameMovement) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        skeletons = objectMapper.readValue(new File(nameFileJson), new TypeReference<List<Skeleton>>(){});
        name = nameMovement;
    }
}
