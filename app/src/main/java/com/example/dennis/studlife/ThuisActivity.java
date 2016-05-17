package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class ThuisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        if (spinner.getItemAtPosition(position).toString() == "College"){
            goCollege();
        } else if (spinner.getItemAtPosition(position).toString() == "Uitgaan") {
            uitgaan();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goCollege(){
        Intent intent = new Intent(this, CollegeActivity.class);
        startActivity(intent);
    }

    public void uitgaan(){
        Intent intent = new Intent(this, UitgaanActivity.class);
        startActivity(intent);
    }
}
