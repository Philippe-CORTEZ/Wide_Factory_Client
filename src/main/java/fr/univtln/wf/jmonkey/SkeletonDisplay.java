package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import fr.univtln.wf.jmonkey.JointDisplay;
import fr.univtln.wf.models.Joint;
import fr.univtln.wf.models.JointEnum;
import fr.univtln.wf.models.Skeleton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString(exclude = "map")
@FieldDefaults(level = AccessLevel.PRIVATE)
/**
 * class that display a skeleton
 */
public class SkeletonDisplay extends Node {
    private Map<JointEnum, Joint> map;
    JointDisplay pelvis;
    JointDisplay navel;
    JointDisplay spineChest;
    JointDisplay neck;
    JointDisplay clavicleLeft;
    JointDisplay shoulderLeft;
    JointDisplay elbowLeft;
    JointDisplay wristLeft;
    JointDisplay handLeft;
    JointDisplay handTipLeft;
    JointDisplay thumbLeft;
    JointDisplay clavicleRight;
    JointDisplay shoulderRight;
    JointDisplay elbowRight;
    JointDisplay wristRight;
    JointDisplay handRight;
    JointDisplay handTipRight;
    JointDisplay thumbRight;
    JointDisplay hipLeft;
    JointDisplay kneeLeft;
    JointDisplay ankleLeft;
    JointDisplay footLeft;
    JointDisplay hipRight;
    JointDisplay kneeRight;
    JointDisplay ankleRight;
    JointDisplay footRight;
    JointDisplay head;
    JointDisplay nose;
    JointDisplay eyeLeft;
    JointDisplay earLeft;
    JointDisplay eyeRight;
    JointDisplay earRight;


    /**
     * constructor
     * initialise the map with the joints of the skeleton,
     * initialise the tree of joints for the skeleton,
     * place joints,
     * attach the root of joints to this
     * @param skeleton skeleton to display
     * @param assetManager
     */
    public SkeletonDisplay(Skeleton skeleton, AssetManager assetManager){
        map = new HashMap<>();
        mappingJoint(skeleton.getJoints());
        initJoints(assetManager);
        refreshJoints(skeleton);
        this.attachChild(pelvis);
        initTree();
    }

    /**
     * initialise all joints to the default value
     * @param assetManager
     */
    public void initJoints(AssetManager assetManager)
    {
        pelvis = new JointDisplay(assetManager, "pelvis");
        navel = new JointDisplay(assetManager, "navel");
        spineChest = new JointDisplay(assetManager, "spineChest");
        neck = new JointDisplay(assetManager, "neck");
        clavicleLeft = new JointDisplay(assetManager, "clavicleLeft");
        shoulderLeft = new JointDisplay(assetManager, "shoulderLeft");
        elbowLeft = new JointDisplay(assetManager, "elbowLeft");
        wristLeft = new JointDisplay(assetManager, "wristLeft");
        handLeft = new JointDisplay(assetManager, "handLeft");
        handTipLeft = new JointDisplay(assetManager, "handTipLeft");
        thumbLeft = new JointDisplay(assetManager, "thumbLeft");
        clavicleRight = new JointDisplay(assetManager, "clavicleRight");
        shoulderRight = new JointDisplay(assetManager, "shoulderRight");
        elbowRight = new JointDisplay(assetManager, "elbowRight");
        wristRight = new JointDisplay(assetManager, "wristRight");
        handRight = new JointDisplay(assetManager, "handRight");
        handTipRight = new JointDisplay(assetManager, "handTipRight");
        thumbRight = new JointDisplay(assetManager, "thumbRight");
        hipLeft = new JointDisplay(assetManager, "hipLeft");
        kneeLeft = new JointDisplay(assetManager, "kneeLeft");
        ankleLeft = new JointDisplay(assetManager, "ankleLeft");
        footLeft = new JointDisplay(assetManager, "footLeft");
        hipRight = new JointDisplay(assetManager, "hipRight");
        kneeRight = new JointDisplay(assetManager, "kneeRight");
        ankleRight = new JointDisplay(assetManager, "ankleRight");
        footRight = new JointDisplay(assetManager, "footRight");
        head = new JointDisplay(assetManager, "head");
        nose = new JointDisplay(assetManager, "nose");
        eyeLeft = new JointDisplay(assetManager, "eyeLeft");
        earLeft = new JointDisplay(assetManager, "earLeft");
        eyeRight = new JointDisplay(assetManager, "eyeRight");
        earRight = new JointDisplay(assetManager, "earRight");
    }

    /**
     * initialise the tree of joints, the root of the tree is the pelvis
     */
    private void initTree(){
        pelvis.getChildren().add(hipLeft);
        pelvis.getChildren().add(hipRight);
        pelvis.getChildren().add(navel);
        navel.getChildren().add(spineChest);
        spineChest.getChildren().add(clavicleLeft);
        spineChest.getChildren().add(clavicleRight);
        spineChest.getChildren().add(neck);
        clavicleLeft.getChildren().add(shoulderLeft);
        shoulderLeft.getChildren().add(elbowLeft);
        elbowLeft.getChildren().add(wristLeft);
        wristLeft.getChildren().add(handLeft);
        wristLeft.getChildren().add(thumbLeft);
        handLeft.getChildren().add(handTipLeft);

        clavicleRight.getChildren().add(shoulderRight);
        shoulderRight.getChildren().add(elbowRight);
        elbowRight.getChildren().add(wristRight);
        wristRight.getChildren().add(handRight);
        wristRight.getChildren().add(thumbRight);
        handRight.getChildren().add(handTipRight);

        neck.getChildren().add(head);
        head.getChildren().add(earLeft);
        head.getChildren().add(earRight);
        head.getChildren().add(eyeLeft);
        head.getChildren().add(eyeRight);
        head.getChildren().add(nose);

        hipLeft.getChildren().add(kneeLeft);
        kneeLeft.getChildren().add(ankleLeft);
        ankleLeft.getChildren().add(footLeft);

        hipRight.getChildren().add(kneeRight);
        kneeRight.getChildren().add(ankleRight);
        ankleRight.getChildren().add(footRight);
    }

    /**
     * refresh all the joints with the ones of the skeletons put on parameter
     * @param skeleton skeleton to display
     */
    public void refreshJoints(Skeleton skeleton)
    {
        mappingJoint(skeleton.getJoints());
        pelvis.place(map.get(JointEnum.K4ABT_JOINT_PELVIS));
        navel.place(map.get(JointEnum.K4ABT_JOINT_SPINE_NAVEL));
        spineChest.place(map.get(JointEnum.K4ABT_JOINT_SPINE_CHEST));
        neck.place(map.get(JointEnum.K4ABT_JOINT_NECK));
        clavicleLeft.place(map.get(JointEnum.K4ABT_JOINT_CLAVICLE_LEFT));
        shoulderLeft.place(map.get(JointEnum.K4ABT_JOINT_SHOULDER_LEFT));
        elbowLeft.place(map.get(JointEnum.K4ABT_JOINT_ELBOW_LEFT));
        wristLeft.place(map.get(JointEnum.K4ABT_JOINT_WRIST_LEFT));
        handLeft.place(map.get(JointEnum.K4ABT_JOINT_HAND_LEFT));
        handTipLeft.place(map.get(JointEnum.K4ABT_JOINT_HANDTIP_LEFT));
        thumbLeft.place(map.get(JointEnum.K4ABT_JOINT_THUMB_LEFT));
        clavicleRight.place(map.get(JointEnum.K4ABT_JOINT_CLAVICLE_RIGHT));
        shoulderRight.place(map.get(JointEnum.K4ABT_JOINT_SHOULDER_RIGHT));
        elbowRight.place(map.get(JointEnum.K4ABT_JOINT_ELBOW_RIGHT));
        wristRight.place(map.get(JointEnum.K4ABT_JOINT_WRIST_RIGHT));
        handRight.place(map.get(JointEnum.K4ABT_JOINT_HAND_RIGHT));
        handTipRight.place(map.get(JointEnum.K4ABT_JOINT_HANDTIP_RIGHT));
        thumbRight.place(map.get(JointEnum.K4ABT_JOINT_THUMB_RIGHT));
        hipLeft.place(map.get(JointEnum.K4ABT_JOINT_HIP_LEFT));
        kneeLeft.place(map.get(JointEnum.K4ABT_JOINT_KNEE_LEFT));
        ankleLeft.place(map.get(JointEnum.K4ABT_JOINT_ANKLE_LEFT));
        footLeft.place(map.get(JointEnum.K4ABT_JOINT_FOOT_LEFT));
        hipRight.place(map.get(JointEnum.K4ABT_JOINT_HIP_RIGHT));
        kneeRight.place(map.get(JointEnum.K4ABT_JOINT_KNEE_RIGHT));
        ankleRight.place(map.get(JointEnum.K4ABT_JOINT_ANKLE_RIGHT));
        footRight.place(map.get(JointEnum.K4ABT_JOINT_FOOT_RIGHT));
        head.place(map.get(JointEnum.K4ABT_JOINT_HEAD));
        nose.place(map.get(JointEnum.K4ABT_JOINT_NOSE));
        eyeLeft.place(map.get(JointEnum.K4ABT_JOINT_EYE_LEFT));
        earLeft.place(map.get(JointEnum.K4ABT_JOINT_EAR_LEFT));
        eyeRight.place(map.get(JointEnum.K4ABT_JOINT_EYE_RIGHT));
        earRight.place(map.get(JointEnum.K4ABT_JOINT_EAR_RIGHT));

    }

    /**
     * mapping between the enum of JointEnum and the Joint that correspond
     * @param joints List of joint to map
     */
    public void mappingJoint(List<Joint> joints)
    {
        for (Joint j : joints) {
            map.put(JointEnum.valueOf(j.getName()), j);
        }
    }
}
