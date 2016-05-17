package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.content.Intent;

/**
 * Created by dennis on 12-5-2016.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Context from;

    public CustomOnItemSelectedListener(Context a){
        this.from = a;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        switch(spinner.getId()){
            case (R.id.spinner ): {
                if (spinner.getItemAtPosition(position).toString() == "College"){
                    goCollege();
                } else if (spinner.getItemAtPosition(position).toString() == "Uitgaan") {
                    uitgaan();
                }
            }
            case R.id.spinner3 : {
                if (spinner.getItemAtPosition(position).toString() == "Huis"){
                    goHome();
                }
                else if (spinner.getItemAtPosition(position).toString() == "College"){
                    goCollege();
                }
            }
            case R.id.spinner2 : {
                if (spinner.getItemAtPosition(position).toString() == "Huis"){
                    goHome();
                }
                else if (spinner.getItemAtPosition(position).toString() == "Uitgaan"){
                    uitgaan();
                }
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void goHome(){
        Intent intent = new Intent(from, ThuisActivity.class);
        from.startActivity(intent);
    }

    public void goCollege(){
        Intent intent = new Intent(from, CollegeActivity.class);
        from.startActivity(intent);
    }

    public void uitgaan(){
        Intent intent = new Intent(from, UitgaanActivity.class);
        from.startActivity(intent);
    }

}
