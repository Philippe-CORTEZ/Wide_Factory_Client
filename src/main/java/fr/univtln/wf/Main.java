//package fr.utln;
//
//import com.jme3.animation.AnimControl;
//import com.jme3.animation.Bone;
//import com.jme3.app.SimpleApplication;
//import com.jme3.material.Material;
//import com.jme3.math.*;
//import com.jme3.scene.Node;
//import com.jme3.scene.debug.SkeletonDebugger;
//
//public class Main extends SimpleApplication {
//
//    Joint jointCou;
//    Joint jointHanche;
//    Joint jointTete;
//    Joint jointJambeG;
//    Joint jointJambeD;
//    Joint jointMainG;
//    Joint jointMainD;
//    Vector3f base = getRootNode().getLocalTranslation();
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        Main t = new Main();
//        t.start();
//    }
//
//    @Override
//    public void simpleInitApp() {
//        Bone[] bones = new Bone[7];
//
//        bones[0] = new Bone("tete");
//        bones[1] = new Bone("cou");
//        bones[2] = new Bone("mainG");
//        bones[3] = new Bone("mainD");
//        bones[4] = new Bone("hanche");
//        bones[5] = new Bone("jambeG");
//        bones[6] = new Bone("jambeD");
//
//        Vector3f tete = new Vector3f(-23.394298553466797f,-760.03021240234375f,1529.9837646484375f).normalize().mult(-1);
//        Vector3f cou = new Vector3f(-15.192566871643066f,-676.73187255859375f,1535.660400390625f).normalize().mult(-1);
//        Vector3f mainG = new Vector3f(742.8292236328125f,-619.22674560546875f,1343.7791748046875f).normalize().mult(-1);
//        Vector3f mainD = new Vector3f(-222.34162902832031f,-4.9096050262451172f,1575.20556640625f).normalize().mult(-1);
//        Vector3f hanche = new Vector3f(11.022367477416992f,-128.26377868652344f,1541.3841552734375f).normalize().mult(-1);
//        Vector3f jambeG = new Vector3f(116.42584228515625f,822.469970703125f, 1603.5809326171875f).normalize().mult(-1);
//        Vector3f jambeD = new Vector3f(-114.06679534912109f,819.99920654296875f,1587.61376953125f).normalize().mult(-1);
//        Quaternion qTete = new Quaternion(0.58974134922027588f,0.52043008804321289f,-0.38432663679122925f,-0.4833742082118988f);
//        Quaternion qCou = new Quaternion(0.50046080350875854f,0.53049683570861816f,-0.51716041564941406f,-0.44794759154319763f);
//        Quaternion qHanche = new Quaternion(0.5250670313835144f,0.55163699388504028f,-0.44963279366493225f,-0.46672439575195312f);
//        Quaternion qMainG = new Quaternion(0.97826480865478516f,0.012599050998687744f,-0.11997875571250916f,0.16865462064743042f);
//        Quaternion qMainD = new Quaternion(0.6288905143737793f,0.66190546751022339f,-0.40573820471763611f,-0.041884042322635651f);
//        Quaternion qJambeD = new Quaternion(0.41249537467956543f,0.53602641820907593f,0.48290041089057922f,0.5561748743057251f);
//        Quaternion qJambeG = new Quaternion(-0.1494266539812088f,-0.12945094704627991f,0.64290285110473633f,0.73999327421188354f);
//
//        bones[0].setBindTransforms(tete, qTete,
//                new Vector3f(1,1,1));
//        bones[1].setBindTransforms(cou, qCou,
//                new Vector3f(1,1,1));
//        bones[2].setBindTransforms(mainG, qMainG,
//                new Vector3f(1,1,1));
//        bones[3].setBindTransforms(mainD, qMainD,
//                new Vector3f(1,1,1));
//        bones[4].setBindTransforms(hanche, qHanche,
//                new Vector3f(1,1,1));
//        bones[5].setBindTransforms(jambeG, qJambeG,
//                new Vector3f(1,1,1));
//        bones[6].setBindTransforms(jambeD, qJambeD,
//                new Vector3f(1,1,1));
//
//        bones[0].getChildren().add(bones[1]);
//        bones[1].getChildren().add(bones[2]);
//        bones[1].getChildren().add(bones[3]);
//        bones[1].getChildren().add(bones[4]);
//        bones[4].getChildren().add(bones[5]);
//        bones[4].getChildren().add(bones[6]);
//
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
//        jointCou = new Joint(cou, qCou, assetManager);
//        jointHanche = new Joint(hanche, qHanche, assetManager);
//        jointTete = new Joint(tete, qTete, assetManager);
//        jointJambeG = new Joint(jambeG, qJambeG, assetManager);
//        jointJambeD = new Joint(jambeD, qJambeD, assetManager);
//        jointMainG = new Joint(mainG, qMainG, assetManager);
//        jointMainD = new Joint(mainD, qMainD, assetManager);
//
//        rootNode.attachChild(jointCou);
//        rootNode.attachChild(jointJambeD);
//        rootNode.attachChild(jointJambeG);
//        rootNode.attachChild(jointMainG);
//        rootNode.attachChild(jointMainD);
//        rootNode.attachChild(jointTete);
//        rootNode.attachChild(jointHanche);
//
//        rootNode.attachChild(node);
//        Material matJointure = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        matJointure.setColor("Color", ColorRGBA.Red);
//
//
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
//    }
//
//    @Override
//    public void simpleUpdate(float tpf) {
////        jointureJambeD.test.rotate(FastMath.QUARTER_PI/15, 0, 0);
////        jointureJambeG.test.rotate(FastMath.QUARTER_PI/15, 0, 0);
//    }
//}