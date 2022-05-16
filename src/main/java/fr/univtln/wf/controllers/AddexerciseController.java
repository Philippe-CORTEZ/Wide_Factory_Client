package fr.univtln.wf.controllers;


import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
import fr.univtln.wf.models.Movement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AddexerciseController   {

    /** movement available in the database */
    @FXML
    private TableView<Movement> movementsDatabase;

    /** movement that fill the exercise */
    @FXML
    private TableView<FragmentExercise> exerciseMovements;

    private Exercise exercise;

    @FXML
    public void initialize()
    {
        initmovement();
        initfinallist();
        // Initialize spinner timer (2 to 10, initial 2, and change value 1 by 1)
        exercise = new Exercise();
    }



    public ObservableList<Movement> getAllmMovement()
    {
        MovementDAO movementDAO = new MovementDAO();

        return FXCollections.observableArrayList(movementDAO.findAll());
    }

    public void initmovement()
    {
        movementsDatabase.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Movement, String> nomCol = new TableColumn<>("name");

        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        nomCol.setMinWidth(400);

        ObservableList<Movement> movements = getAllmMovement();
        movementsDatabase.setItems(movements);
        movementsDatabase.getColumns().addAll(nomCol);
    }

    void initfinallist(){
        TableColumn<FragmentExercise, String> nomCol = new TableColumn<>("name");
        TableColumn repetition = new TableColumn("repetition");
        repetition.setEditable(true);

        nomCol.setCellValueFactory(new PropertyValueFactory<>("movement/name"));
        repetition.setCellValueFactory(new PropertyValueFactory<FragmentExercise, Integer>("repetition"));
        exerciseMovements.getColumns().addAll(nomCol, repetition);


    }


    @FXML
    void addmovement() {

        Movement movement =  movementsDatabase.getSelectionModel().getSelectedItem();



        l.add(movement);
        ObservableList<Movement> movements;
        movements = FXCollections.observableArrayList(l);


        exerciseMovements.setItems(movements);
    }



}
