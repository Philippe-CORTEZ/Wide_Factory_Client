package fr.univtln.wf.test_mains;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.databases.daos.PersonDAO;
import fr.univtln.wf.models.*;

import java.io.IOException;

public class TestInsertExercise {
    public static void main(String[] args) throws IOException
    {
        Movement m = new Movement("src/main/resources/movement_coucou.json", "coucou");
        Person p = Person.builder().firstname("Zakk").lastname("Wide").pseudo("Sketard").roleEnum(RoleEnum.COACH).build();

        PersonDAO personDAO = new PersonDAO();
        personDAO.persist(p);

        MovementDAO movementDAO = new MovementDAO();
        movementDAO.persist(m);


//        Exercise exo = Exercise.builder().description("UwU").name("hello2").creator(p).build();
//        exo.addMovement(m, 1);
//        exo.addMovement(m, 1);
//
//        ExerciseDAO eDAO = new ExerciseDAO();
//        eDAO.persist(exo);

    }
}
