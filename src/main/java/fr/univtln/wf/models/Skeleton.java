package fr.univtln.wf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


/**
 * Represent the whole Skeleton of a person for one frame
 * @author Wide Factory Team
 */
@Slf4j

@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "SKELETON", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"frame", "movement" })
})
public class Skeleton
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The frame number 1 is the first frame */
    @JsonProperty("frame")
    private int frame;

    /** List of join objects that represent 3D skeleton */
    @JsonProperty("joints")
    @OneToMany(mappedBy = "skeleton")
    private List<Joint> joints;

    /** movement that the skeleton refer */
    @ManyToOne
    @JoinColumn(referencedColumnName = "NAME", name = "NAME_MOVEMENT")
    private Movement movement;

    @Transient
    private Map<JointEnum, Joint> map = new EnumMap<>(JointEnum.class);

    @Getter
    @Transient
    private static final Map<JointEnum, List<JointEnum>> mapBones = new EnumMap<>(JointEnum.class);



    /**
     * Create a list of Skeleton objects with a JSON array format from string
     * The list can contain juste one skeleton or more
     * @param stringJSONArray the string formatted in JSON that represent the data of the skeletons
     * @return a list of Skeleton objects that correspond with the JSON array
     */
    public static List<Skeleton> newInstance(String stringJSONArray)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Skeleton> skeletons = null;

        try
        {
            skeletons = objectMapper.readValue(stringJSONArray, new TypeReference<>(){});
        }
        catch (JsonProcessingException error)
        {
            log.error("Error when parsing JSON for creating list of skeleton objects ", error);
        }
        return skeletons;
    }



    /**
     * setter of joints
     * @param joints
     */
    public void setJoints(List<Joint> joints) {
        this.joints = joints;
        // update the mapping of joints
        mappingJoint();
    }

    /**
     * mapping between the enum of JointEnum and the Joint that correspond
     */
    public void mappingJoint()
    {
        map.clear();
        for (Joint j : joints) {
            map.put(JointEnum.valueOf(j.getName()), j);
        }
    }


    static
    {
        // mapping of bones hierarchy
        List<JointEnum> pelvisHeredity = new ArrayList<>();
        List<JointEnum> hipRightHeredity = new ArrayList<>();
        List<JointEnum> hipLeftHeredity = new ArrayList<>();
        List<JointEnum> kneeRightHeredity = new ArrayList<>();
        List<JointEnum> kneeLeftHeredity = new ArrayList<>();
        List<JointEnum> ankleRightHeredity = new ArrayList<>();
        List<JointEnum> ankleLeftHeredity = new ArrayList<>();
        List<JointEnum> spineNavelHeredity = new ArrayList<>();
        List<JointEnum> spineChestHeredity = new ArrayList<>();
        List<JointEnum> clavicleRightHeredity = new ArrayList<>();
        List<JointEnum> clavicleLeftHeredity = new ArrayList<>();
        List<JointEnum> neckHeredity = new ArrayList<>();
        List<JointEnum> shoulderRightHeredity = new ArrayList<>();
        List<JointEnum> shoulderLeftHeredity = new ArrayList<>();
        List<JointEnum> elbowLeftHeredity = new ArrayList<>();
        List<JointEnum> elbowRightHeredity = new ArrayList<>();
        List<JointEnum> wristRightHeredity = new ArrayList<>();
        List<JointEnum> wristLeftHeredity = new ArrayList<>();
        List<JointEnum> handRightHeredity = new ArrayList<>();
        List<JointEnum> handLeftHeredity = new ArrayList<>();

        pelvisHeredity.add(JointEnum.K4ABT_JOINT_HIP_LEFT);
        pelvisHeredity.add(JointEnum.K4ABT_JOINT_HIP_RIGHT);
        pelvisHeredity.add(JointEnum.K4ABT_JOINT_SPINE_NAVEL);

        hipLeftHeredity.add(JointEnum.K4ABT_JOINT_KNEE_LEFT);
        kneeLeftHeredity.add(JointEnum.K4ABT_JOINT_ANKLE_LEFT);
        ankleLeftHeredity.add(JointEnum.K4ABT_JOINT_FOOT_LEFT);

        hipRightHeredity.add(JointEnum.K4ABT_JOINT_KNEE_RIGHT);
        kneeRightHeredity.add(JointEnum.K4ABT_JOINT_ANKLE_RIGHT);
        ankleRightHeredity.add(JointEnum.K4ABT_JOINT_FOOT_RIGHT);

        spineNavelHeredity.add(JointEnum.K4ABT_JOINT_SPINE_CHEST);
        spineChestHeredity.add(JointEnum.K4ABT_JOINT_CLAVICLE_LEFT);
        spineChestHeredity.add(JointEnum.K4ABT_JOINT_CLAVICLE_RIGHT);
        spineChestHeredity.add(JointEnum.K4ABT_JOINT_NECK);

        clavicleLeftHeredity.add(JointEnum.K4ABT_JOINT_SHOULDER_LEFT);
        shoulderLeftHeredity.add(JointEnum.K4ABT_JOINT_ELBOW_LEFT);
        elbowLeftHeredity.add(JointEnum.K4ABT_JOINT_WRIST_LEFT);
        wristLeftHeredity.add(JointEnum.K4ABT_JOINT_HAND_LEFT);
        wristLeftHeredity.add(JointEnum.K4ABT_JOINT_THUMB_LEFT);
        handLeftHeredity.add(JointEnum.K4ABT_JOINT_HANDTIP_LEFT);

        clavicleRightHeredity.add(JointEnum.K4ABT_JOINT_SHOULDER_RIGHT);
        shoulderRightHeredity.add(JointEnum.K4ABT_JOINT_ELBOW_RIGHT);
        elbowRightHeredity.add(JointEnum.K4ABT_JOINT_WRIST_RIGHT);
        wristRightHeredity.add(JointEnum.K4ABT_JOINT_HAND_RIGHT);
        wristRightHeredity.add(JointEnum.K4ABT_JOINT_THUMB_RIGHT);
        handRightHeredity.add(JointEnum.K4ABT_JOINT_HANDTIP_RIGHT);

        neckHeredity.add(JointEnum.K4ABT_JOINT_HEAD);

        mapBones.put(JointEnum.K4ABT_JOINT_PELVIS, pelvisHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_HIP_LEFT, hipLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_KNEE_LEFT, kneeLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_ANKLE_LEFT, ankleLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_ANKLE_RIGHT, ankleRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_HIP_RIGHT, hipRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_KNEE_RIGHT, kneeRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_SPINE_NAVEL, spineNavelHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_SPINE_CHEST, spineChestHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_CLAVICLE_LEFT, clavicleLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_CLAVICLE_RIGHT, clavicleRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_SHOULDER_LEFT, shoulderLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_SHOULDER_RIGHT, shoulderRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_ELBOW_LEFT, elbowLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_ELBOW_RIGHT, elbowRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_WRIST_LEFT, wristLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_WRIST_RIGHT, wristRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_HAND_LEFT, handLeftHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_HAND_RIGHT, handRightHeredity);
        mapBones.put(JointEnum.K4ABT_JOINT_NECK, neckHeredity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skeleton skeleton = (Skeleton) o;
        return id != 0 && Objects.equals(id, skeleton.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
