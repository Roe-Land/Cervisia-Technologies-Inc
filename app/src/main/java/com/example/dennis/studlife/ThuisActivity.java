package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThuisActivity extends AppCompatActivity{
    private Student student;
    private ProgressBar een;
    private ProgressBar twee;
    private ProgressBar drie;
    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);


        student = student.get(this);
        student.checkDead(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener( new CustomOnItemSelectedListener(this, student));

        setProgressBars();

    }



    public void setProgressBars(){
        een = (ProgressBar) findViewById(R.id.progressBar);
        twee = (ProgressBar) findViewById(R.id.progressBar2);
        drie = (ProgressBar) findViewById(R.id.progressBar3);
        een.setProgress(student.getEnergie());
        twee.setProgress(student.getGeluk());
        drie.setProgress(student.getGezondheid());
    }

    public void drinkMilk(View v){
        int u = student.getGezondheid();
        drie.setProgress( u + 5);
        student.setGezondheid(u + 5, this);
    }

    @Override
    protected void onStop(){
        student.save(this);
        super.onStop();
    }

    @Override
    public void onDestroy(){
        student.save(this);
        Time.setAppCloses(Time.intsToString());
        super.onDestroy();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        student.save(this);
        finishAffinity();
    }

}
