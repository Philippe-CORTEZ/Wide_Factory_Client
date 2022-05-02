package fr.utln;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

public class JointDisplay extends Node {
    Geometry geometry;

    public JointDisplay(AssetManager assetManager)
    {
        Sphere s = new Sphere(16, 16, 0.010f);
        geometry = new Geometry("Jointure", s);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geometry.setMaterial(mat);
        this.attachChild(geometry);
    }
    
    public void place(Joint joint)
    {
        if (joint != null)
        {
            geometry.setLocalTranslation(new Vector3f(-joint.getX(), -joint.getY(), joint.getZ()).normalize());
            geometry.setLocalRotation(new Quaternion(joint.getW(), -joint.getWx(), -joint.getWy(), joint.getWz()));
        }
    }
}
