package fr.utln;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Skeleton {
    private int frame;
    private List<Joint> joints;
}
