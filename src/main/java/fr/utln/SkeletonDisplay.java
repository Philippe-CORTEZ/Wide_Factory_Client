package fr.utln;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
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


    public SkeletonDisplay(Skeleton skeleton, AssetManager assetManager){
        map = new HashMap<>();
        initJoints(skeleton, assetManager);
        refreshJoints(skeleton);
        this.attachChild(pelvis);
        initTree();
    }

    public void initJoints(Skeleton skeleton, AssetManager assetManager)
    {
        mappingJoint(skeleton.getJoints());

        pelvis = new JointDisplay(assetManager);
        navel = new JointDisplay(assetManager);
        spineChest = new JointDisplay(assetManager);
        neck = new JointDisplay(assetManager);
        clavicleLeft = new JointDisplay(assetManager);
        shoulderLeft = new JointDisplay(assetManager);
        elbowLeft = new JointDisplay(assetManager);
        wristLeft = new JointDisplay(assetManager);
        handLeft = new JointDisplay(assetManager);
        handTipLeft = new JointDisplay(assetManager);
        thumbLeft = new JointDisplay(assetManager);
        clavicleRight = new JointDisplay(assetManager);
        shoulderRight = new JointDisplay(assetManager);
        elbowRight = new JointDisplay(assetManager);
        wristRight = new JointDisplay(assetManager);
        handRight = new JointDisplay(assetManager);
        handTipRight = new JointDisplay(assetManager);
        thumbRight = new JointDisplay(assetManager);
        hipLeft = new JointDisplay(assetManager);
        kneeLeft = new JointDisplay(assetManager);
        ankleLeft = new JointDisplay(assetManager);
        footLeft = new JointDisplay(assetManager);
        hipRight = new JointDisplay(assetManager);
        kneeRight = new JointDisplay(assetManager);
        ankleRight = new JointDisplay(assetManager);
        footRight = new JointDisplay(assetManager);
        head = new JointDisplay(assetManager);
        nose = new JointDisplay(assetManager);
        eyeLeft = new JointDisplay(assetManager);
        earLeft = new JointDisplay(assetManager);
        eyeRight = new JointDisplay(assetManager);
        earRight = new JointDisplay(assetManager);
    }

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

    public void mappingJoint(List<Joint> joints)
    {
        for (Joint j : joints) {
            map.put(JointEnum.valueOf(j.getName()), j);
        }
    }
}
