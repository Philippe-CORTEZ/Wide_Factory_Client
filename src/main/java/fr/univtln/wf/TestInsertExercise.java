package fr.univtln.wf;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.databases.daos.PersonDAO;
import fr.univtln.wf.models.*;

import java.io.IOException;

public class TestInsertExercise {
    public static void main(String[] args) throws IOException
    {
//        MovementDAO movementDAO = new MovementDAO();
        Movement m = new Movement("src/main/resources/movement_coucou.json", "coucou");
//        Movement m = movementDAO.find("coucou");
//        // TODO : put it in DAO
        for(Skeleton skeleton : m.getSkeletons())
        {
            skeleton.mappingJoint();
        }
//
//        PersonDAO pdao = new PersonDAO();
        Person p = Person.builder().firstname("pcule").lastname("cucurbi").pseudo("moncucurbitace").roleEnum(RoleEnum.COACH).build();
//
        Exercise exo = Exercise.builder().description("UwU").name("courrier").creator(p).build();
//
        exo.addMovement(m);
        ExerciseDAO eDAO = new ExerciseDAO();
//        m.getExercises().add(exo);
        eDAO.persist(exo);

    }
}
