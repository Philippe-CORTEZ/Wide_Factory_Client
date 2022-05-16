package fr.univtln.wf.controllers;


import fr.univtln.wf.databases.daos.MovementDAO;
import fr.univtln.wf.models.Exercise;
import fr.univtln.wf.models.FragmentExercise;
import fr.univtln.wf.models.Movement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class AddexerciseController   {



    @FXML
    private TableView<Movement> finalmovements;

    @FXML
    private TableView<Movement> listofmovements;


    static List<Movement> l = new ArrayList<>();




    @FXML
    public void initialize()
    {
        initmovement();
        initfinallist();
        // Initialize spinner timer (2 to 10, initial 2, and change value 1 by 1)
    }



    public ObservableList<Movement> getAllmMovement()
    {
        MovementDAO movementDAO = new MovementDAO();

        return FXCollections.observableArrayList(movementDAO.findAll());
    }

    public void initmovement()
    {
        listofmovements.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        TableColumn<Movement, String> nomCol = new TableColumn<>("name");

        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        nomCol.setMinWidth(400);

        ObservableList<Movement> movements = getAllmMovement();
        listofmovements.setItems(movements);
        listofmovements.getColumns().addAll(nomCol);
        //


    }


    void initfinallist(){
        TableColumn<Movement, String> nomCol = new TableColumn<>("name");
       // TableColumn repscount = new TableColumn("repscount");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       // repscount.setEditable(true);

        //repscount.setCellValueFactory(new PropertyValueFactory<Movement, TextField>("firstName"));
        finalmovements.getColumns().addAll(nomCol);


    }


    @FXML
    void addmovement() {

        Movement movement =  listofmovements.getSelectionModel().getSelectedItem();



        l.add(movement);
        ObservableList<Movement> movements;
        movements = FXCollections.observableArrayList(l);


        finalmovements.setItems(movements);
    }



}
