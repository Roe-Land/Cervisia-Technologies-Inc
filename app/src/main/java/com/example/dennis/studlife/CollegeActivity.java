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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);



        student = student.get(this);
        student.checkDead(this);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));
        ProgressBar zeven = (ProgressBar) findViewById(R.id.progressBar7);
        ProgressBar acht = (ProgressBar) findViewById(R.id.progressBar8);
        ProgressBar negen = (ProgressBar) findViewById(R.id.progressBar9);
        zeven.setProgress(student.getEnergie());
        acht.setProgress(student.getGeluk());
        negen.setProgress(student.getGezondheid());
    }

    @Override
    protected void onStop(){
        student.save(this);
        super.onStop();

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        student.save(this);
        finishAffinity();
    }
}
