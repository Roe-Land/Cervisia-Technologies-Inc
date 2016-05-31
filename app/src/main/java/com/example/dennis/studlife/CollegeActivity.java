package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class CollegeActivity extends AppCompatActivity {
    private Student student;
    private ProgressBar zeven;
    private ProgressBar acht;
    private ProgressBar negen;
    private Spinner spinner;
    private ImageView stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        student = (Student) getIntent().getSerializableExtra("student");
        student.checkDead(this);

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));

        setProgressBars();
        student.setProgressBars(negen, acht, zeven);

        stud = (ImageView) findViewById(R.id.studanimation);

        stud.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });
    }

    public void setProgressBars(){
        zeven = (ProgressBar) findViewById(R.id.progressBar7);
        acht = (ProgressBar) findViewById(R.id.progressBar8);
        negen = (ProgressBar) findViewById(R.id.progressBar9);
        zeven.setProgress(student.getEnergy());
        acht.setProgress(student.getHappiness());
        negen.setProgress(student.getHealth());
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
