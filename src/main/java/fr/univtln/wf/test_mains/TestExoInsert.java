package fr.univtln.wf.test_mains;

import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.databases.daos.PersonDAO;
import fr.univtln.wf.models.Exercise;

public class TestExoInsert {
    public static void main(String[] args) {
        ExerciseDAO ed = new ExerciseDAO();
        PersonDAO pd = new PersonDAO();
        MovementDAO md = new MovementDAO();

        Exercise e = new Exercise();
        e.setCreator(pd.find("moncucurbitace"));
        e.addMovement(md.find("extension"));
        e.addMovement(md.find("coucou"));
        e.addMovement(md.find("squat"));
        e.setName("exercice efficace");

        ed.persist(e);
    }
}
