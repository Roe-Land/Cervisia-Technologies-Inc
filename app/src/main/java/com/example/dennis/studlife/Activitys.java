package com.example.dennis.studlife;

import android.view.DragEvent;
import android.widget.ImageView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Activities class. Custom interface using 3 methods, which is implemented in the different activities/locations for our stud.
 */
public interface Activitys {
    /**
     * Method that can be implemented to set the progressbars of health, energy and joy.
     */
    void setProgressBarsAndTextViewsValues();

    /**
     * Method that can be implemented with a switch, to differentiate between our different types of food/drinks our stud can consume.
     * @param v The consumable that is selected.
     * @param event The DragEvent.
     */
    void dropSwitch(ImageView v, DragEvent event);

    /**
     * Method that can be implemented to start the chewing animation of our stud.
     * @param event The DragEvent.
     */
    void dropDrinkOrFood(DragEvent event);
}
