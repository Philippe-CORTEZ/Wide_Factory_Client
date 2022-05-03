package fr.univtln.wf;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import lombok.Getter;

/**
 * class that Display a joint by a geometry in attribute
 */
@Getter
public class JointDisplayable extends Node {
    private final Geometry geometry;

    /**
     * constructor
     * set to the attribute the default value of the geometry and attach it to this
     * default: blue sphere with a radius of 0.010f
     * @param assetManager
     */
    public JointDisplayable(String name, AssetManager assetManager)
    {
        super(name);
        Sphere s = new Sphere(16, 16, 0.010f);
        geometry = new Geometry("Jointure", s);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geometry.setMaterial(mat);
        this.attachChild(geometry);
    }

    /**
     * place the geometry attribute to the position of the joint
     * @param joint the joint to display
     */
    public void place(Joint joint)
    {
        if (joint != null)
        {
            geometry.setName(joint.getName());
            geometry.setLocalTranslation(new Vector3f(-joint.getX(), -joint.getY(), joint.getZ()).normalize());
            geometry.setLocalRotation(new Quaternion(joint.getW(), -joint.getWx(), -joint.getWy(), joint.getWz()));
        }
        else
        {
            geometry.setName("null");
        }
    }
}
