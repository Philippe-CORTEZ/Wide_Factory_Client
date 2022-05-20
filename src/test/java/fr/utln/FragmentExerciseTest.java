package fr.utln;

import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
import fr.univtln.wf.models.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FragmentExerciseTest {
    FragmentExercise fragmentTest = new FragmentExercise();

    @Test
    void TestEquals()
    {
        FragmentExercise fragment = FragmentExercise.builder()
                .exercise(new Exercise())
                .movement(new Movement())
                .position(3)
                .repetition(1).build();

        Assertions.assertNotEquals(fragment, fragmentTest);

        fragment.setPosition(0);
        Assertions.assertNotEquals(fragment, fragmentTest);

        fragment.setExercise(fragmentTest.getExercise());
        Assertions.assertNotEquals(fragment, fragmentTest);

        fragment.setMovement(fragmentTest.getMovement());
        Assertions.assertEquals(fragment, fragmentTest);

        fragment.setExercise(new Exercise());
        Assertions.assertNotEquals(fragment, fragmentTest);
        fragment.setExercise(fragmentTest.getExercise());

        fragment.setPosition(3);
        Assertions.assertNotEquals(fragment, fragmentTest);
    }

    @Test
    void TestHashCode()
    {
        Exercise ex = new Exercise();
        Movement mv = new Movement();
        ex.setName("develop");
        mv.setName("curl");
        FragmentExercise fragment = FragmentExercise.builder()
                .exercise(ex)
                .movement(mv)
                .position(3)
                .repetition(1).build();

        Assertions.assertNotEquals(fragment.hashCode(), fragmentTest.hashCode());

        fragment.setPosition(0);
        Assertions.assertNotEquals(fragment.hashCode(), fragmentTest.hashCode());

        ex.setName(fragmentTest.getExercise().getName());
        Assertions.assertNotEquals(fragment.hashCode(), fragmentTest.hashCode());

        mv.setName(fragmentTest.getMovement().getName());
        Assertions.assertEquals(fragment.hashCode(), fragmentTest.hashCode());

        ex.setName("curl");
        Assertions.assertNotEquals(fragment.hashCode(), fragmentTest.hashCode());
        ex.setName(fragmentTest.getExercise().getName());

        fragment.setPosition(3);
        Assertions.assertNotEquals(fragment.hashCode(), fragmentTest.hashCode());
    }

    @Test
    void TestConstuctorDefault()
    {
        Assertions.assertEquals(null, fragmentTest.getMovement());
        Assertions.assertEquals(0 , fragmentTest.getPosition());
        Assertions.assertEquals(5 , fragmentTest.getRepetition());
        Assertions.assertEquals(null, fragmentTest.getMovement());
    }

    @Test
    void TestBuilderDefault()
    {
        FragmentExercise fragment = FragmentExercise.builder().build();

        Assertions.assertEquals(null, fragment.getMovement());
        Assertions.assertEquals(0 , fragment.getPosition());
        Assertions.assertEquals(5 , fragment.getRepetition());
        Assertions.assertEquals(null, fragment.getMovement());
    }

}
