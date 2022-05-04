package fr.univtln.wf.jmonkey;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;
import fr.univtln.wf.models.JointEnum;
import fr.univtln.wf.models.Skeleton;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;


/**
 * Class that display a skeleton
 * @author Wide Factory Team
 */
@Getter
public class SkeletonDisplayable extends Node
{
    /** Use to get the displayable joint by it's name */
    Map<JointEnum, JointDisplayable> jointDisplayableMap;


    /** Default constructor */
    public SkeletonDisplayable()
    {
        this.jointDisplayableMap = new EnumMap<>(JointEnum.class);
    }


    /**
     * Display the joints of a skeleton
     * @param skeleton skeleton to display joints
     * @param assetManager assetManager of Jmonkey
     */
    private void displayJoints(Skeleton skeleton, AssetManager assetManager)
    {
        JointDisplayable jointDisplayable;
        // iterate all joint from the EnumJoint
        for(JointEnum j : JointEnum.values())
        {
            // if the joint exist display it
            if (skeleton.getMap().get(j) != null)
            {
                jointDisplayable = new JointDisplayable(j.name(), assetManager);
                jointDisplayable.place(skeleton.getMap().get(j));
                this.attachChild(jointDisplayable);
                jointDisplayableMap.put(j, jointDisplayable);
            }
        }
    }

    /**
     * display all the bones of the skeleton
     * @param skeleton the skeleton to display the bones
     */
    private void displayBones(Skeleton skeleton, AssetManager assetManager)
    {
        Geometry bone;

        // iterate all key from the mapping of bones of skeleton, the key is the parent joint
        for(JointEnum j1 : Skeleton.getMapBones().keySet())
        {
            // if exist
            if (skeleton.getMap().get(j1) != null)
            {
                JointDisplayable joint1 = jointDisplayableMap.get(j1);
                // iterate all children joints
                for (JointEnum j2 : Skeleton.getMapBones().get(j1))
                {
                    // if exist
                    if (skeleton.getMap().get(j2) != null)
                    {
                        JointDisplayable joint2 = jointDisplayableMap.get(j2);
                        // generate a bone and display it
                        bone = generateBone(joint1, joint2, assetManager);
                        this.attachChild(bone);
                    }
                }
            }
        }
    }

    /**
     * Generate a geometry that represent a bone between 2 joint
     * @param joint1 the first joint
     * @param joint2 the second joint
     * @param assetManager assetManager of Jmonkey
     * @return the bone geometry
     */
    private Geometry generateBone(JointDisplayable joint1, JointDisplayable joint2, AssetManager assetManager)
    {
        // use for the length of the bone
        float distance = joint1.getGeometry().getLocalTranslation().distance(joint2.getGeometry().getLocalTranslation());

        Cylinder t = new Cylinder(20, 50, 0.005f, distance, true);
        Geometry geom = new Geometry("Cylinder", t);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);

        geom.setMaterial(mat);

        // put the bone to the center between joint1 and joint2
         Vector3f position = new Vector3f();
        joint1.getGeometry().getLocalTranslation().add(joint2.getGeometry().getLocalTranslation(), position);
        position.divideLocal(2);

        geom.setLocalTranslation(position);
        // orient the bone up to the joint
        geom.lookAt(joint1.getGeometry().getLocalTranslation(), joint2.getGeometry().getLocalTranslation());
        return geom;
    }

    /**
     * Display a skeleton (his joints and bones)
     * @param skeleton the skeleton to display
     */
    public void displaySkeleton(Skeleton skeleton, AssetManager assetManager)
    {
        // clear olds value and put the new ones
        this.detachAllChildren();
        jointDisplayableMap.clear();
        displayJoints(skeleton, assetManager);
        displayBones(skeleton, assetManager);
    }
}