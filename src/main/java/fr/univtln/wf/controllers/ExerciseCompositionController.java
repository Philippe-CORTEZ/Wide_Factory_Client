package fr.univtln.wf.controllers;

import fr.univtln.wf.jmonkey.Visualize;
import fr.univtln.wf.models.FragmentExercise;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller of the composition exercise window
 */
public class ExerciseCompositionController
{
    @FXML
    private Label nameExercise;

    @FXML
    private TableView<FragmentExercise> exerciseFragment;

    /**
     * initialize the display of the windows
     */
    public void initialize()
    {
        initListFragment();
        initNameExercise();
    }

    /**
     * initialize the list of element that compose an exercise
     */
    private void initListFragment()
    {
        TableColumn<FragmentExercise, String> nameColumn = new TableColumn<>("name");
        TableColumn<FragmentExercise, Integer> repetitionColumn = new TableColumn<>("repetition");

        // Set type of column that describe name of a movement
        // It's just the movement name
        nameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMovement().getName()) );
        repetitionColumn.setCellValueFactory(new PropertyValueFactory<>( "repetition" ));

        // Add the customised column to the tableView
        exerciseFragment.getColumns().addAll(nameColumn, repetitionColumn);
        exerciseFragment.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        exerciseFragment.setItems(FXCollections.observableArrayList(DataGUI.getExerciseSelected().getFragments()));
    }

    /**
     * initialize the text that display the name of the exercise
     */
    private void initNameExercise()
    {
        nameExercise.setText(DataGUI.getExerciseSelected().getName());
        nameExercise.setAlignment(Pos.CENTER);
    }

    /**
     * visualize a fragment of exercise selected
     */
    public void visualize()
    {
        if (exerciseFragment.getSelectionModel().selectedItemProperty().getValue() != null)
        {
            new Visualize().visualizeMovement(exerciseFragment.getSelectionModel().selectedItemProperty().getValue().getMovement());
        }
    }
}
