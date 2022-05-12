package fr.univtln.wf.controllers;

import com.jme3.system.AppSettings;
import fr.univtln.wf.jmonkey.JMEExercise;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Controller that manage pop up window options when an exercise is double clicked
 * @author Wide Factory Team
 */
public class ExercisePopupController
{
    @FXML
    private Label exerciseDescription;


    @FXML
    public void initialize()
    {
        exerciseDescription.setText("description : " + DataGUI.getExerciseSelected().getDescription());
    }

    /** Visualize reference exercise with reference movements */
    public void visualize()
    {
        JMEExercise jme = new JMEExercise();
        jme.getExoDisplayable().setExercise(DataGUI.getExerciseSelected());

        jme.setShowSettings(false);

        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "My awesome Game");
        settings.put("VSync", true);
        settings.put("Samples", 4);

        jme.setSettings(settings);

        jme.start();
    }

}
