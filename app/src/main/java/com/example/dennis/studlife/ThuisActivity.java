package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class ThuisActivity extends AppCompatActivity implements Activitys{
    private Student student;
    private ProgressBar een;
    private ProgressBar twee;
    private ProgressBar drie;
    private Spinner spinner;
    private ImageView stud;
    private TextView social;
    private TextView study;
    private TextView money;
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            money.setText(msg.what + " Studs");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        student = ApplicationClass.student;
        super.onCreate(savedInstanceState);
        student.checkDead(this);
        setContentView(R.layout.activity_thuis);


        setProgressBarsAndTextViews();
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

    public void updateSocialStudy(int socialPoints, int studyPoints){
        social.setText("Social: " + socialPoints);
        study.setText("Study: " + studyPoints);
    }


    public void setProgressBarsAndTextViews(){
        Handler uiHandler = new Handler();
        een = (ProgressBar) findViewById(R.id.progressBar);
        twee = (ProgressBar) findViewById(R.id.progressBar2);
        drie = (ProgressBar) findViewById(R.id.progressBar3);
        een.setProgress(student.getEnergy());
        twee.setProgress(student.getHappiness());
        drie.setProgress(student.getHealth());
        money = (TextView) findViewById(R.id.money);
        money.setText(student.getMoney() + " Studs");
        social = (TextView) findViewById(R.id.socialPoints);
        social.setText("Social: " + student.getSocialeGod());
        study = (TextView) findViewById(R.id.studyPoints);
        study.setText("Study: " + student.getStudieVoortgang());
    }

    @Override
    public void setProgressBarsAndTextViewsValues(){
        een.setProgress(student.getEnergy());
        twee.setProgress(student.getHappiness());
        drie.setProgress(student.getHealth());
        uiHandler.sendMessage(uiHandler.obtainMessage(student.getMoney()));

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
