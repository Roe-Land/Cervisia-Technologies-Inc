package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class ThuisActivity extends AppCompatActivity{
    public static final String PREFS_NAME = "MyPrefsFile";
    private Student student;
    private boolean newGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener( new CustomOnItemSelectedListener(this, student));
        if (newGame){
            student = student.get(this);
        }else{
            this.student = new Student();
            newGame = true;
        }
        ProgressBar een = (ProgressBar) findViewById(R.id.progressBar);
        ProgressBar twee = (ProgressBar) findViewById(R.id.progressBar2);
        ProgressBar drie = (ProgressBar) findViewById(R.id.progressBar3);
        een.setProgress(student.getEnergie());
        twee.setProgress(student.getGeluk());
        drie.setProgress(student.getGezondheid());
    }



    @Override
    protected void onStop(){
        super.onStop();
        student.save(this);


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}
