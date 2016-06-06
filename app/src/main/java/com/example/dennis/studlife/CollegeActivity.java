package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
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
    private Spinner studySpinner;
    private TextView social;
    private TextView study;
    private ImageView stud;
    private TextView money;
    private Handler animationSwitchHandler = new Handler();
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

        studySpinner = (Spinner) findViewById(R.id.study);
        studySpinner.setOnItemSelectedListener(new CustomOnItemSelectedListenerStudySleep(student, "study", spinner, this));
        //spinner.setEnabled(false);
        //studySpinner.setEnabled(false);


        student.updateProgressbars();

        stud = (ImageView) findViewById(R.id.studanimation);

        stud.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });

        findViewById(R.id.brood).setOnTouchListener(longClick);
        findViewById(R.id.water).setOnTouchListener(longClick);
        findViewById(R.id.energie).setOnTouchListener(longClick);
        findViewById(R.id.chips).setOnTouchListener(longClick);
        findViewById(R.id.cola).setOnTouchListener(longClick);

        findViewById(R.id.mouth).setOnDragListener(DropListener);
    }

    public void setEnable(){
        spinner.setEnabled(false);
        studySpinner.setEnabled(false);
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
        uiHandler.sendMessage(uiHandler.obtainMessage((int)student.getMoney()));
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

    View.OnTouchListener longClick = new View.OnTouchListener()
    {
        public boolean onTouch (View v, MotionEvent event)
        {
            DragShadow dragShadow = new DragShadow(v);
            ClipData data = ClipData.newPlainText("","");
            v.startDrag(data,dragShadow,v,0);
            return false;
        }
    };

    private class DragShadow extends View.DragShadowBuilder
    {
        public DragShadow(View view)
        {
            super(view);
        }
        @Override
        public void onDrawShadow(Canvas canvas)
        {
            super.onDrawShadow(canvas);
        }
        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint)
        {
            super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
        }
    }

    View.OnDragListener DropListener = new View.OnDragListener()
    {
        public boolean onDrag(View v, DragEvent event)
        {
            int dragEvent = event.getAction();

            switch (dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    ((AnimationDrawable) stud.getBackground()).stop();
                    stud.setBackgroundResource(R.drawable.stud1_eten_1);
                    Log.i("Drag Event", "Entered");
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    stud.setBackgroundResource(R.drawable.studanimation);
                    ((AnimationDrawable) stud.getBackground()).start();
                    Log.i("Drag Event", "Exited");
                    break;

                case DragEvent.ACTION_DROP:
                    Log.i("Drag Event", "Dropped");
                    stud.setBackgroundResource(R.drawable.studanimation_kauwen);
                    ((AnimationDrawable) stud.getBackground()).start();

                    animationSwitchHandler.postDelayed(new Runnable(){
                        public void run(){
                            stud.setBackgroundResource(R.drawable.studanimation);
                            ((AnimationDrawable) stud.getBackground()).start();
                        }
                    },3000);
                    ImageView dragged = (ImageView) event.getLocalState();
                    dropSwitch(dragged, getApplicationContext());
            }
            return true;
        }
    };

    public void dropSwitch(ImageView v, Context context){
        switch (v.getId()){
            case R.id.brood :
                student.eatBread(context);
                break;

            case R.id.chips :
                student.eatCrisps(context);
                break;

            case R.id.cola :
                student.drinkCola(context);
                break;

            case R.id.energie :
                student.drinkEnergy(context);
                break;

            default :
                student.drinkWater(context);
                break;
        }
        student.updateProgressbars();
    }

}