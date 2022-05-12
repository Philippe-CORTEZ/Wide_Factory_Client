package fr.univtln.wf.controllers;

import fr.univtln.wf.models.Exercise;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data which controllers need and can modify
 * @author Wide Factory Team
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataGUI
{
    /** The exercise selected in the exercises list */
    @Getter
    @Setter
    private static Exercise exerciseSelected;

    /** Time of recording wished by a coach */
    @Getter
    @Setter
    private static int timeRecording;

    /** Name of movement that will be recorded */
    @Getter
    @Setter
    private static String movementNameRecording;

    /** Description of movement that will be recorded */
    @Getter
    @Setter
    private static String movementDescriptionRecording;

}
