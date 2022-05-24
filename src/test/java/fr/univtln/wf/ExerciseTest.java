package fr.univtln.wf;

import fr.univtln.wf.models.*;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ExerciseTest
{
    @Getter
    @Setter
    Exercise exerciseTest = new Exercise();

    @Test
    void TestBuilderDefault()
    {
        Exercise exercise = Exercise.builder().build();
        Assertions.assertEquals(0, exercise.getFragments().size());
        Assertions.assertEquals("", exercise.getDescription());
        Assertions.assertEquals("", exercise.getName());
        Assertions.assertNull(exercise.getCreator().getPseudo());
    }

    @Test
    void TestConstructorDefault()
    {
        Exercise exercise = new Exercise();
        Assertions.assertEquals(0, exercise.getFragments().size());
        Assertions.assertEquals("", exercise.getDescription());
        Assertions.assertEquals("", exercise.getName());
        Assertions.assertNull(exercise.getCreator().getPseudo());
    }

    @Test
    void TestAddMovement() throws IOException {
        Movement movementTest = new Movement("src/test/java/fr/univtln/wf/jsonTest/movement_1_skeleton.json", "movement test");
        Exercise exercise = new Exercise();
        exercise.addMovement(movementTest);
        Assertions.assertEquals(1, exercise.getFragments().size());
        Assertions.assertEquals(exercise, exercise.getFragments().get(0).getExercise());
        Assertions.assertEquals(movementTest, exercise.getFragments().get(0).getMovement());
        Assertions.assertEquals(5 , exercise.getFragments().get(0).getRepetition());
        Assertions.assertEquals(0 , exercise.getFragments().get(0).getPosition());
    }

    @Test
    void TestAddMovementRep() throws IOException {
        Movement movementTest = new Movement("src/test/java/fr/univtln/wf/jsonTest/movement_1_skeleton.json", "movement test");
        Exercise exercise = new Exercise();
        exercise.addMovement(movementTest, 3);

        Assertions.assertEquals(1, exercise.getFragments().size());
        Assertions.assertEquals(exercise, exercise.getFragments().get(0).getExercise());
        Assertions.assertEquals(movementTest, exercise.getFragments().get(0).getMovement());
        Assertions.assertEquals(3 , exercise.getFragments().get(0).getRepetition());
        Assertions.assertEquals(0 , exercise.getFragments().get(0).getPosition());
    }

    @Test
    void TestMappingSkeletons() throws IOException
    {
        Exercise exercise = new Exercise();
        Movement movement = new Movement("src/test/java/fr/univtln/wf/jsonTest/movement_1_skeleton.json", "movement test");
        exercise.addMovement(movement);
        MovementTest movementTest = new MovementTest();
        for(FragmentExercise fragmentExercise : exercise.getFragments())
        {
            movementTest.setFirstMovement(fragmentExercise.getMovement());
            movementTest.TestMappingSkeletons();
        }
        Assertions.assertTrue(true);
    }

    @Test
    void TestEquals()
    {
        Exercise exercise = new Exercise();
        Assertions.assertEquals(exercise, exerciseTest);

        exercise.setCreator(Person.builder().pseudo("maria").roleEnum(RoleEnum.COACH).lastname("test").lastname("roof").build());
        Assertions.assertEquals(exercise, exerciseTest);

        exercise.setDescription("description test");
        Assertions.assertEquals(exercise, exerciseTest);

        exercise.setName("exo 1");
        Assertions.assertNotEquals(exercise, exerciseTest);
    }

    @Test
    void TestHashCode()
    {
        Exercise exercise = new Exercise();
        Assertions.assertEquals(exercise.hashCode(), exerciseTest.hashCode());

        exercise.setCreator(Person.builder().pseudo("maria").roleEnum(RoleEnum.COACH).lastname("test").lastname("roof").build());
        Assertions.assertEquals(exercise.hashCode(), exerciseTest.hashCode());

        exercise.setDescription("description test");
        Assertions.assertEquals(exercise.hashCode(), exerciseTest.hashCode());

        exercise.setName("exo 1");
        Assertions.assertNotEquals(exercise.hashCode(), exerciseTest.hashCode());
    }
}
