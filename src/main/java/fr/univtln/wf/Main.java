package fr.univtln.wf;


import com.jme3.app.SimpleApplication;
import java.io.IOException;

public class Main extends SimpleApplication
{
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        Main t = new Main();
        Movement movement = new Movement("src/main/resources/data.json", "Test");
        System.out.println(movement);

//        t.start();
    }

    @Override
    public void simpleInitApp() {
//        Bone[] bones = new Bone[7];
//
//        com.jme3.animation.Skeleton skeleton = new com.jme3.animation.Skeleton(bones);
//        skeleton.updateWorldVectors();
//        skeleton.setBindingPose();
//
//        AnimControl control = new AnimControl(skeleton);
//
//        Node node = new Node();
//        node.addControl(control);
//
//        rootNode.attachChild(node);
//        cam.setLocation(new Vector3f(0,0, 2));
//
//        SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton",
//                control.getSkeleton());
//        Material mat2 = new Material(assetManager,
//                "Common/MatDefs/Misc/Unshaded.j3md");
//        mat2.setColor("Color", ColorRGBA.Red);
//        mat2.getAdditionalRenderState().setDepthTest(false);
//        skeletonDebug.setMaterial(mat2);
//        node.attachChild(skeletonDebug);
    }

    @Override
    public void simpleUpdate(float tpf) {
//        jointureJambeD.test.rotate(FastMath.QUARTER_PI/15, 0, 0);
//        jointureJambeG.test.rotate(FastMath.QUARTER_PI/15, 0, 0);
    }
}