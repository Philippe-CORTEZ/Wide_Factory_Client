package fr.univtln.wf.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Class that represent a joint of a skeleton
 * A joint is represented by:
 * His name
 * 4 floats for the quaternion
 * 3 floats for the coordinates
 * @author Wide Factory Team
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@ToString
@EqualsAndHashCode(of = {"name", "w", "wx", "wy", "wz", "x", "y", "z"})

@Getter
@Setter
@Entity
public class Joint
{
    @Transient
    private String name;
    private float w;
    private float wx;
    private float wy;
    private float wz;
    private float x;
    private float y;
    private float z;
}
