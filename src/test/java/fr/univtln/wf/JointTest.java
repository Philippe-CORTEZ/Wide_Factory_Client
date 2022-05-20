package fr.univtln.wf;

import fr.univtln.wf.models.Joint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JointTest {

    /**
     * test the equals function in joint
     */
    @Test
    void TestEquals()
    {
        Joint j1 = Joint.builder()
                .name("first joint")
                .x(1)
                .y(1)
                .z(1)
                .w(1)
                .wx(1)
                .wy(1)
                .wz(1).build();
        Joint j2 = Joint.builder()
            .name("second joint")
            .x(1)
            .y(1)
            .z(1)
            .w(1)
            .wx(1)
            .wy(1)
            .wz(1).build();
        Assertions.assertNotEquals(j1, j2);

        j2.setName(j1.getName());
        Assertions.assertEquals(j1, j2);

        j2 = Joint.builder()
            .name("first joint")
            .x(2)
            .y(2)
            .z(2)
            .w(2)
            .wx(2)
            .wy(2)
            .wz(2).build();
        Assertions.assertEquals(j1, j2);

        j2.setName("second joint");
        Assertions.assertNotEquals(j1, j2);
    }

    @Test
    void TestHashCode()
    {
        Joint j1 = Joint.builder()
                .name("first joint")
                .x(1)
                .y(1)
                .z(1)
                .w(1)
                .wx(1)
                .wy(1)
                .wz(1).build();
        Joint j2 = Joint.builder()
                .name("second joint")
                .x(1)
                .y(1)
                .z(1)
                .w(1)
                .wx(1)
                .wy(1)
                .wz(1).build();
        Assertions.assertNotEquals(j1.hashCode(), j2.hashCode());

        j2.setName(j1.getName());
        Assertions.assertEquals(j1.hashCode(), j2.hashCode());

        j2 = Joint.builder()
                .name("first joint")
                .x(2)
                .y(2)
                .z(2)
                .w(2)
                .wx(2)
                .wy(2)
                .wz(2).build();
        Assertions.assertEquals(j1.hashCode(), j2.hashCode());

        j2.setName("second joint");
        Assertions.assertNotEquals(j1.hashCode(), j2.hashCode());
    }
}
