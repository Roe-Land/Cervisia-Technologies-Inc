package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by dennis on 12-5-2016.
 */
public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Activity from;
    private Student student;

    public CustomOnItemSelectedListener(Context a, Student student){
        this.from = (Activity) a;
        this.student = student;
    }

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



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goHome(){
        Intent intent = new Intent(from, ThuisActivity.class);
        from.startActivity(intent);
        from.finish();
    }

    public void goCollege(){
        Intent intent = new Intent(from, CollegeActivity.class);
        from.startActivity(intent);
        from.finish();
    }

    public void uitgaan(){
        Intent intent = new Intent(from, UitgaanActivity.class);
        from.startActivity(intent);
        from.finish();;
    }

}
