package fr.univtln.wf.controllers;


import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
import fr.univtln.wf.models.Movement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AddExerciseController {

    /** movement available in the database */
    @FXML
    private TableView<Movement> movementsDatabase;

    /** movement that fill the exercise */
    @FXML
    private TableView<FragmentExercise> exerciseMovements;

    /** the name of the exercise which will be created */
    @FXML
    private TextField nameExercise;

    /** label used to display exeption error to the user */
    @FXML
    private Label errorLabel;

    /** exercise that will be generated */
    private Exercise exercise;

    /**
     * initialize attribute
     */
    @FXML
    public void initialize()
    {
        // Init upper tableview from database
        initMovementDatabase();
        // Init lower tableview with user choices
        initExerciseMovements();
        // Initialize spinner timer (2 to 10, initial 2, and change value 1 by 1)
        exercise = new Exercise();
    }

    /**
     * get all movement in the database
     * @return ObservableList that contain all Movement in database
     */
    public ObservableList<Movement> getAllMovement()
    {
        MovementDAO movementDAO = new MovementDAO();

        return FXCollections.observableArrayList(movementDAO.findAll());
    }

    /**
     * initialize the list of movement that are in database
     */
    public void initMovementDatabase()
    {

        TableColumn<Movement, String> nomCol = new TableColumn<>("name");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nomCol.setMinWidth(400);

        ObservableList<Movement> movements = getAllMovement();
        movementsDatabase.setItems(movements);
        movementsDatabase.getColumns().addAll(nomCol);
        movementsDatabase.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * initialize the list of fragmentExercise of the exercise to create
     */
    void initExerciseMovements(){
        TableColumn<FragmentExercise, String> nomCol = new TableColumn<>("name");
        TableColumn<FragmentExercise, Spinner<Integer>> repetition = new TableColumn<>("repetition");
        repetition.setEditable(true);

        repetition.setCellValueFactory(new PropertyValueFactory<>("repetition"));
        nomCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMovement().getName()) );

        // Add columns to lower tableview
        exerciseMovements.getColumns().addAll(nomCol, repetition);
        exerciseMovements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /** Delete a movement from the list of movements of the exercise */
    public void deleteMovement()
    {
        if (exerciseMovements.getSelectionModel().getSelectedItem() != null)
        {
            FragmentExercise fragment = exerciseMovements.getSelectionModel().getSelectedItem();

            exercise.getFragments().remove(fragment);
            exerciseMovements.setItems(FXCollections.observableArrayList(exercise.getFragments()));
        }
    }

    /**
     * persist the exercise created if possible
     */
    public void validate()
    {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        if (nameExercise.getText() == null || nameExercise.getText().equals(""))
        {
            errorLabel.setText("please enter a non null exercise name");
        }
        else if (exerciseDAO.find(nameExercise.getText()) != null)
        {
            errorLabel.setText("an exercise already have this name, please change it");
        }
        else
        {
            exercise.setName(nameExercise.getText());
            exercise.setCreator(DataGUI.getCurrentUser());
            // put the right position of fragments
            for (int i = 0; i < exercise.getFragments().size() ; i++ )
            {
                exercise.getFragments().get(i).setPosition(i);
            }

            exerciseDAO.persist(exercise);
            exercise = new Exercise();
            ((Stage)(nameExercise.getScene().getWindow())).close();
            errorLabel.setText("");
        }
    }

    /**
     * add a movement to the exercise
     */
    public void addMovement()
    {
        if(movementsDatabase.getSelectionModel().getSelectedItem() != null)
        {
            Movement movement =  movementsDatabase.getSelectionModel().getSelectedItem();

            exercise.addMovement(movement);
            exerciseMovements.setItems(FXCollections.observableArrayList(exercise.getFragments()));
        }
    }
}
