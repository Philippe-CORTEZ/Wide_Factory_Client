package fr.univtln.wf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


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

@Getter
@Setter

@Entity
@Table(name = "JOINT", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "id_skeleton" }) })
public class Joint
{
    /** ID in database */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Name of joint describe a part of body */
    private String name;

    /** 3D informations like position and quaternion info */
    private float w;
    private float wx;
    private float wy;
    private float wz;
    private float x;
    private float y;
    private float z;


    /** Overriding equals */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Joint)) return false;

        Joint joint = (Joint) o;

        return Objects.equals(name, joint.name);
    }

    /** Overriding hashCode */
    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

    /** Overriding toString */
    @Override
    public String toString()
    {
        return "Joint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", w=" + w +
                ", wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

}
