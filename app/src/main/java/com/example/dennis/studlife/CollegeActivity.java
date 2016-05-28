package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class CollegeActivity extends AppCompatActivity {
    private Student student;
    private ProgressBar zeven;
    private ProgressBar acht;
    private ProgressBar negen;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        student = student.get(this);
        student.checkDead(this);

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));

        setProgressBars();

    }

    public void setProgressBars(){
        zeven = (ProgressBar) findViewById(R.id.progressBar7);
        acht = (ProgressBar) findViewById(R.id.progressBar8);
        negen = (ProgressBar) findViewById(R.id.progressBar9);
        zeven.setProgress(student.getEnergie());
        acht.setProgress(student.getGeluk());
        negen.setProgress(student.getGezondheid());
    }

    @Override
    protected void onStop(){
        student.save(this);
        Time.setAppCloses(Time.intsToString());
        super.onStop();

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        student.save(this);
        finishAffinity();
    }
}
