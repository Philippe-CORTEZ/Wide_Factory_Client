package fr.utln;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
/**
 * class that represent a skeleton
 */
public class Skeleton {
    private int frame;
    private List<Joint> joints;
}
