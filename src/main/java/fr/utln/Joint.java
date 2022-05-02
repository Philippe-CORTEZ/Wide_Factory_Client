package fr.utln;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Joint {
    private String name;
    private float w;
    private float wx;
    private float wy;
    private float wz;
    private float x;
    private float y;
    private float z;
}
