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

    /** skeleton that the joint belong to */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "ID", name = "ID_SKELETON")
    private Skeleton skeleton;

    /** Name of joint describe a part of body */
    private String name;
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

        if (!Objects.equals(skeleton, joint.skeleton)) return false;
        return Objects.equals(name, joint.name);
    }

    /** Overriding hashCode */
    @Override
    public int hashCode()
    {
        int result = skeleton != null ? skeleton.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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
