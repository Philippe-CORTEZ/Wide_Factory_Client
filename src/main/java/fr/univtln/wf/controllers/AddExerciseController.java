package fr.univtln.wf.controllers;


import fr.univtln.wf.databases.daos.ExerciseDAO;
import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
import fr.univtln.wf.models.Movement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * Controller to add an exercise with movements that already exist in database as a coach
 * @author Wide Factory Team
 */
public class AddExerciseController
{
    /** movement available in the database */
    @FXML
    private TableView<Movement> movementsDatabase;

    /** movement that fill the exercise */
    @FXML
    private TableView<FragmentExercise> exerciseMovements;

    /** the name of the exercise which will be created */
    @FXML
    private TextField nameExercise;

    /** label used to display exception error to the user */
    @FXML
    private Label errorLabel;

    /** exercise that will be generated */
    private Exercise exercise;


    /** Initialize attribute */
    @FXML
    public void initialize()
    {
        // Init upper tableview from database
        initMovementDatabase();
        // Init lower tableview with user choices
        initExerciseMovements();

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

    /** Initialize the list of movement that are in database */
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

    /** Initialize the list of fragmentExercise of the exercise to create */
    void initExerciseMovements()
    {
        TableColumn<FragmentExercise, String> nameColumn = new TableColumn<>("name");
        TableColumn<FragmentExercise, Spinner<Integer>> repetitionColumn = new TableColumn<>("repetition");

        // Set type of value in column that describe the repetition of a movement
        // A Spinner widget from 1 to 20 by 1 in increment / decrement
        repetitionColumn.setCellValueFactory(fragment ->
        {
            FragmentExercise framgentExercise = fragment.getValue();
            Spinner<Integer> repetitionValue = new Spinner<>(1, 20, 10, 1);
            framgentExercise.setRepetition(1);
            repetitionValue.valueProperty().addListener((obs, oldValue, newValue) -> framgentExercise.setRepetition(newValue) );
            return new SimpleObjectProperty<>(repetitionValue);
        });

        // Set type of column that describe name of a movement
        // It's just the movement name
        nameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMovement().getName()) );

        // Add the customised column to the tableView
        exerciseMovements.getColumns().addAll(nameColumn, repetitionColumn);
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

    /** Persist the exercise created if possible */
    public void validate()
    {
        ExerciseDAO exerciseDAO = new ExerciseDAO();

        // If the name is not right display an error
        if (nameExercise.getText() == null || nameExercise.getText().equals(""))
        {
            errorLabel.setText("please enter a non null exercise name");
        }
        else if (exerciseDAO.find(nameExercise.getText()) != null)
        {
            errorLabel.setText("an exercise already have this name, please change it");
        }

        // If the name is right create an exercise object with right parameters and persist it
        else
        {
            exercise.setName(nameExercise.getText());
            exercise.setCreator(DataGUI.getCurrentUser());

            // put fragments to right position
            for (int i = 0; i < exercise.getFragments().size() ; i++ )
            {
                exercise.getFragments().get(i).setPosition(i);
            }

            exerciseDAO.persist(exercise);

            // Reset data for a new add and close window
            exercise = new Exercise();
            ((Stage)(nameExercise.getScene().getWindow())).close();
            errorLabel.setText("");
        }
    }

    /** Add a movement to the futur exercise in the tableview */
    public void addMovement()
    {
        if(movementsDatabase.getSelectionModel().getSelectedItem() != null)
        {
            Movement movement =  movementsDatabase.getSelectionModel().getSelectedItem();

            // Add movement in exercise object and refresh table view
            exercise.addMovement(movement);
            exerciseMovements.setItems(FXCollections.observableArrayList(exercise.getFragments()));
        }
    }

}
