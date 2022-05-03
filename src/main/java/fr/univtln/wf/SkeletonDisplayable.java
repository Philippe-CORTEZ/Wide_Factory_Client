package fr.univtln.wf;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Cylinder;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

/**
 * class that display a skeleton
 */
@Getter
public class SkeletonDisplayable extends Node {
    Map<JointEnum, JointDisplayable> jointDisplayableMap = new EnumMap<>(JointEnum.class);


    /**
     * set values of JointDisplayMap with a list of joint
     * @param skeleton skeleton to set at the map
     * @param assetManager
     */
    private void displayJoints(Skeleton skeleton, AssetManager assetManager)
    {
        JointDisplayable jointDisplayable;
        for(JointEnum j : JointEnum.values())
        {
            jointDisplayable = new JointDisplayable(j.name(), assetManager);
            jointDisplayable.place(skeleton.getMap().get(j));
            this.attachChild(jointDisplayable);
            jointDisplayableMap.put(j, jointDisplayable);
        }
    }

    /**
     * display all the bones of the skeleton
     * @param skeleton the skeleton to diplay the bones
     */
    private void displayBones(Skeleton skeleton, AssetManager assetManager)
    {
//        Geometry bone;

        for(JointEnum j1 : Skeleton.getMapBones().keySet())
        {
            if (skeleton.getMap().get(j1) != null)
            {
                JointDisplayable joint1 = jointDisplayableMap.get(j1);
                for (JointEnum j2 : Skeleton.getMapBones().get(j1))
                {
                    if (skeleton.getMap().get(j2) != null)
                    {
                        JointDisplayable joint2 = jointDisplayableMap.get(j2);
                        generateBone(joint1, joint2, assetManager);
                    }
                }
            }
        }
    }

    private Geometry generateBone(JointDisplayable joint1, JointDisplayable joint2, AssetManager assetManager)
    {
        float distance = joint1.getGeometry().getLocalTranslation().distance(joint2.getGeometry().getLocalTranslation());
        Cylinder t = new Cylinder(20, 50, 0.01f, distance, true);
        Geometry geom = new Geometry("Cylinder", t);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);

        geom.setMaterial(mat);

        Vector3f position = new Vector3f();
        joint1.getGeometry().getLocalTranslation().add(joint2.getGeometry().getLocalTranslation(), position);
        position.divideLocal(2);
        geom.setLocalTranslation(position);
//        geom.lookAt(geom.getLocalTranslation(), new Vector3f(100, 10, 0));
        this.attachChild(geom);

        return geom;
    }

    /**
     * display a skeleton
     * @param skeleton the skeleton to display
     */
    public void displaySkeleton(Skeleton skeleton, AssetManager assetManager)
    {
        this.detachAllChildren();
        jointDisplayableMap.clear();
        displayJoints(skeleton, assetManager);
        displayBones(skeleton, assetManager);
    }
}