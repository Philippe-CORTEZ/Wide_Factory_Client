package fr.univtln.wf;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import lombok.SneakyThrows;


import static java.lang.Thread.sleep;

public class JME extends SimpleApplication {

    public static Movement movement = new Movement();
    private static SkeletonDisplay sk;
    private static int count = 0;


    /**
     * init of Jmonkey display
     */
    @Override
    public void simpleInitApp()
    {
        cam.setLocation(new Vector3f(0,0, 3));
        // display the first skeleton of the movement
        sk = new SkeletonDisplay(movement.getSkeletons().get(0), assetManager);
        System.out.println(sk.getAnkleLeft().geometry);
        rootNode.attachChild(sk);
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

    @SneakyThrows
    @Override
    public void simpleUpdate(float tpf) {
        sleep(33); // to slow down the frame rate
        if (count < movement.getSkeletons().size())
        {
            sk.refreshJoints(movement.getSkeletons().get(count)); // refresh the skeleton with the following one
            count++;
        }
        else count = 0; // restart the animation to the start
    }
}