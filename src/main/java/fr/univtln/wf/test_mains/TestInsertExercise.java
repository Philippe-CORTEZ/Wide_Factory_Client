package fr.univtln.wf.tmps;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.models.*;

import java.io.IOException;

public class TestInsertExercise {
    public static void main(String[] args) throws IOException
    {
        Movement m = new Movement("src/main/resources/movement_coucou.json", "coucou");

        Person p = Person.builder().firstname("pcule").lastname("cucurbi").pseudo("moncucurbitace").roleEnum(RoleEnum.COACH).build();
//
        Exercise exo = Exercise.builder().description("UwU").name("courrier").creator(p).build();
//
        exo.addMovement(m);
        ExerciseDAO eDAO = new ExerciseDAO();
        eDAO.persist(exo);

    }
}
