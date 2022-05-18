package fr.utln;

import fr.univtln.wf.models.Joint;
import fr.univtln.wf.models.JointEnum;
import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Setter
public class SkeletonTest {
    private Movement firstMovement;
    private Skeleton skeleton;

    public SkeletonTest() throws IOException {
        firstMovement = new Movement("src/test/java/fr/utln/jsonTest/movement_1_skeleton.json", "first movement");
        skeleton = firstMovement.getSkeletons().get(0);
    }

    @Test
    void TestSetJoints() {
        List<Joint> joints = skeleton.getJoints();
        Skeleton skeleton = new Skeleton();
        skeleton.setJoints(joints);
        Assertions.assertEquals(skeleton.getJoints(), joints);
        for (Joint j : skeleton.getJoints())
        {
            Assertions.assertEquals(skeleton.getMap().get(JointEnum.valueOf(j.getName())), j);
        }
    }

    @Test
    void TestMappingJoint()
    {
        skeleton.mappingJoint();
        for (Joint j : skeleton.getJoints())
        {
            Assertions.assertEquals(skeleton.getMap().get(JointEnum.valueOf(j.getName())), j);
        }
    }

    @Test
    void TestEquals()
    {
        Skeleton secondSkeleton = new Skeleton();
        secondSkeleton.setFrame(skeleton.getFrame());
        Assertions.assertEquals(secondSkeleton, skeleton);

        secondSkeleton.setFrame(skeleton.getFrame() + 1);
        Assertions.assertNotEquals(secondSkeleton, skeleton);
    }
}
