package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;




public class ThuisActivity extends AppCompatActivity implements Activitys{
    private Student student;
    private ProgressBar een;
    private ProgressBar twee;
    private ProgressBar drie;
    private Spinner spinner;
    private ImageView stud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);
        student = (Student) getIntent().getSerializableExtra("student");
        student.checkDead(this);

        setProgressBars();
        student.setClass(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener( new CustomOnItemSelectedListener(this, student));

        student.updateProgressbars();

        stud = (ImageView) findViewById(R.id.studanimation);

        stud.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });
    }


    public void setProgressBars(){
        een = (ProgressBar) findViewById(R.id.progressBar);
        twee = (ProgressBar) findViewById(R.id.progressBar2);
        drie = (ProgressBar) findViewById(R.id.progressBar3);
        een.setProgress(student.getEnergy());
        twee.setProgress(student.getHappiness());
        drie.setProgress(student.getHealth());
    }

    @Override
    public void setProgressBarsValues(){
        een.setProgress(student.getEnergy());
        twee.setProgress(student.getHappiness());
        drie.setProgress(student.getHealth());
    }


    @Override
    protected void onStop(){
        student.save(this);
        super.onStop();
    }

    @Override
    public void onDestroy(){
        student.save(this);
        super.onDestroy();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        student.save(this);
        finishAffinity();
    }

}
