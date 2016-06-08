package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * A custom OnItemSelectedListener. Used for switching between various locations(college, home, city) in the app.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Activity from;

    /**
     * Constructor. Sets the current context (current location) to a local variable so it can be used.
     * @param a current context.
     */
    public CustomOnItemSelectedListener(Context a){
        this.from = (Activity) a;
    }

    /**
     * Method used to define which activity to go to after the location is selected in-app.
     * Calls to several methods defined below, which start the new activities and thus the new locations.
     *
     * @param parent The AdapterView
     * @param view The view
     * @param position The option that has been selected on the spinner
     * @param id ID.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        TextView spin = ((TextView)spinner.getChildAt(0));
        spin.setTextColor(Color.RED);
        spin.setTextSize(20);

        switch(spinner.getId()){
            case (R.id.spinner ): {
                if (position == 1){
                    goCollege();
                } else if (position == 2) {
                    uitgaan();
                }
                break;
            }
            case R.id.spinner2 : {
                if (position == 1){
                    goHome();
                }
                else if (position == 2){
                    uitgaan();
                }
                break;
            }
            case R.id.spinner3 : {
                if (position == 1){
                    goHome();
                }
                if (position == 2){
                    goCollege();
                }
                break;
            }
        }
    }

    /**
     * Method used to do nothing when nothing is selected.
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Tells our stud to go home, and let's the app switch to the home activity.
     */
    public void goHome(){
        Intent intent = new Intent(from, ThuisActivity.class);
        from.startActivity(intent);
        from.finish();
    }

    /**
     * Tells our stud to go to college, and let's the app switch to the college activity.
     */
    public void goCollege(){
        Intent intent = new Intent(from, CollegeActivity.class);
        from.startActivity(intent);
        from.finish();
    }

    /**
     * Tells our stud to go to the bar, and let's the app switch to the city activity.
     */
    public void uitgaan(){
        Intent intent = new Intent(from, UitgaanActivity.class);
        from.startActivity(intent);
        from.finish();;
    }
}
