package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CollegeActivity extends AppCompatActivity implements Activitys{
    private Student student;
    private ProgressBar zeven;
    private ProgressBar acht;
    private ProgressBar negen;
    private Spinner spinner;
    private TextView social;
    private TextView study;
    private ImageView stud;
    private TextView money;
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            money.setText(msg.what + " Studs");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        student = ApplicationClass.student;
        student.checkDead(this);

        setProgressBarsAndTextViews();
        student.setClass(this);

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));

        student.updateProgressbars();

        stud = (ImageView) findViewById(R.id.studanimation);

        stud.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });
    }

    public void updateSocialStudy(int socialPoints, int studyPoints){
        social.setText("Social: " + socialPoints);
        study.setText("Study: " + studyPoints);
    }


    public void setProgressBarsAndTextViews(){
        zeven = (ProgressBar) findViewById(R.id.progressBar7);
        acht = (ProgressBar) findViewById(R.id.progressBar8);
        negen = (ProgressBar) findViewById(R.id.progressBar9);
        zeven.setProgress(student.getEnergy());
        acht.setProgress(student.getHappiness());
        negen.setProgress(student.getHealth());
        money = (TextView) findViewById(R.id.money2);
        money.setText(student.getMoney() + " Studs");
        social = (TextView) findViewById(R.id.socialPoints);
        social.setText("Social: " + student.getSocialeGod());
        study = (TextView) findViewById(R.id.studyPoints);
        study.setText("Study: " + student.getStudieVoortgang());
    }

    @Override
    public void setProgressBarsAndTextViewsValues(){
        zeven.setProgress(student.getEnergy());
        acht.setProgress(student.getHappiness());
        negen.setProgress(student.getHealth());
        uiHandler.sendMessage(uiHandler.obtainMessage(student.getMoney()));
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
