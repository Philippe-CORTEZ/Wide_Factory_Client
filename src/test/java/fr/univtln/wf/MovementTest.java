package fr.univtln.wf;

import fr.univtln.wf.models.Movement;
import fr.univtln.wf.models.Skeleton;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class MovementTest {
    @Setter
    @Getter
    private Movement firstMovement = new Movement("src/test/java/fr/univtln/wf/jsonTest/movement_1_skeleton.json", "first movement");

    public MovementTest() throws IOException {
    }

    @Test
    void TestBuilderDefault()
    {
        Movement m = Movement.builder().build();
        Assertions.assertEquals(0, m.getSkeletons().size());
        Assertions.assertEquals("", m.getName());
        Assertions.assertEquals("", m.getDescription());
    }

    @Test
    void TestDefaultConstuctor()
    {
        Movement m = new Movement();
        Assertions.assertEquals(0, m.getSkeletons().size());
        Assertions.assertEquals("", m.getName());
        Assertions.assertEquals("", m.getDescription());
    }

    @Test
    void TestEquals()
    {
        Movement m = new Movement();
        m.setName(firstMovement.getName());
        Assertions.assertEquals(m.getName(), firstMovement.getName());
    }

    @Test
    void TestHashCode()
    {
        Movement m = new Movement();
        m.setName(firstMovement.getName());
        Assertions.assertEquals(m.hashCode(), firstMovement.hashCode());
    }

    @Test
    void TestClear()
    {
        Movement m = Movement.builder()
                .description(firstMovement.getDescription())
                .name(firstMovement.getName())
                .skeletons(new ArrayList<>(firstMovement.getSkeletons())).build();
        m.clear();

        Assertions.assertEquals(0, m.getSkeletons().size());
        Assertions.assertEquals("", m.getName());
        Assertions.assertEquals("", m.getDescription());
    }

    @Test
    void TestMappingSkeletons() throws IOException {
        SkeletonTest skeletonTest = new SkeletonTest();
        for(Skeleton skeleton : firstMovement.getSkeletons())
        {
            skeletonTest.setSkeleton(skeleton);
            skeletonTest.TestMappingJoint();
        }

    }

    @Test
    void TestContructorWithJsonName() throws IOException {
        Movement m = new Movement("src/test/java/fr/univtln/wf/jsonTest/movement_1_skeleton.json", "first movement");
        Assertions.assertEquals("first movement", m.getName());
        Assertions.assertEquals(1, m.getSkeletons().size());
        Assertions.assertEquals("", m.getDescription());
    }
}
