//package fr.utln;
//
//import com.jme3.asset.AssetManager;
//import com.jme3.material.Material;
//import com.jme3.math.ColorRGBA;
//import com.jme3.math.FastMath;
//import com.jme3.math.Quaternion;
//import com.jme3.math.Vector3f;
//import com.jme3.scene.Geometry;
//import com.jme3.scene.Node;
//import com.jme3.scene.shape.Cylinder;
//import com.jme3.scene.shape.Sphere;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//@Builder
//public class Joint extends Node {
//    private float w;
//    private float wx;
//    private float wy;
//    private float wz;
//    private float x;
//    private float y;
//    private float z;
//
//    public Joint(Vector3f joint, Quaternion quat, AssetManager assetManager) {
//        Material matJointure = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        matJointure.setColor("Color", ColorRGBA.Red);
//
//        Sphere sprere = new Sphere(20, 50, 0.01f);
//        Geometry geo = new Geometry("jointure", sprere);
//        geo.setMaterial(matJointure);
//
//
//
//        Material matAxeX = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        matAxeX.setColor("Color", ColorRGBA.Yellow);
//        Material matAxeY = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        matAxeY.setColor("Color", ColorRGBA.Blue);
//        Material matAxeZ = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        matAxeZ.setColor("Color", ColorRGBA.Green);
//
//
//        Cylinder axe = new Cylinder(20, 50, 0.005f,0.075f,  true);
//        Geometry geoAxeX = new Geometry("axe", axe);
//        geoAxeX.setMaterial(matAxeX);
//        geoAxeX.setLocalTranslation(0.075f/2, 0,0);
//        geoAxeX.rotate(0, FastMath.HALF_PI,0);
//
//
//        Geometry geoAxeY = new Geometry("axe", axe);
//        geoAxeY.setMaterial(matAxeY);
//        geoAxeY.setLocalTranslation(0, 0.075f/2, 0);
//        geoAxeY.rotate(FastMath.HALF_PI, 0,0);
//
//        Geometry geoAxeZ = new Geometry("axe", axe);
//        geoAxeZ.setMaterial(matAxeZ);
//        geoAxeZ.setLocalTranslation(0, 0, + 0.075f/2);
//
//        this.setLocalTranslation(joint);
//        this.rotate(quat);
//        this.attachChild(geo);
//        this.attachChild(geoAxeX);
//        this.attachChild(geoAxeY);
//        this.attachChild(geoAxeZ);
//    }
//}
