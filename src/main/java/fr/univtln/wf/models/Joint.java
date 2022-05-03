package fr.univtln.wf.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
/**
 * class that represent a joint of a skeleton
 * caracterise by:
 * name of the joint
 * 4 float for the quaternion
 * 3 float for the coordinate
 */
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
