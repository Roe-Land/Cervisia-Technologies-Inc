package com.example.dennis.studlife;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by dennis on 12-5-2016.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Class from;



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;


        if((spinner.getId() == R.id.spinner) & ( (Spinner) (spinner.getId()).getItemIdAtPosition(position).toString() == "Uit") ){

        }

        switch(spinner.getId()){
            case (R.id.spinner & spinner.getItemIdAtPosition(position).toString == "Huis"): {
                if (){

                }
            }
            case "Uitgaan" : {

            }
            case "College" : {

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
