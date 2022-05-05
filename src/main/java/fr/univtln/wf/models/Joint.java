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
@Table(name = "JOINT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "skeleton" })
})
public class Joint
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** skeleton that the joint belong to */
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID", name = "ID_SKELETON")
    private Skeleton skeleton;

    private String name;
    private float w;
    private float wx;
    private float wy;
    private float wz;
    private float x;
    private float y;
    private float z;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joint joint = (Joint) o;
        return id != 0 && Objects.equals(id, joint.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
