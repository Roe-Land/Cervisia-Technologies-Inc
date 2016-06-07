package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
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
        setContentView(R.layout.activity_thuis);
        student = ApplicationClass.student;
        student.checkDead(this);

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

        findViewById(R.id.peer).setOnTouchListener(new OnTouch());
        findViewById(R.id.melk).setOnTouchListener(new OnTouch());
        findViewById(R.id.appel).setOnTouchListener(new OnTouch());
        findViewById(R.id.brood).setOnTouchListener(new OnTouch());
        findViewById(R.id.wortel).setOnTouchListener(new OnTouch());

        findViewById(R.id.mouth).setOnDragListener(new DropListener(stud, this));
    }

    public void updateSocialStudy(int socialPoints, int studyPoints){
        social.setText("Social: " + socialPoints);
        study.setText("Study: " + studyPoints);
    }


    public void setProgressBarsAndTextViews(){
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
        uiHandler.sendMessage(uiHandler.obtainMessage((int) student.getMoney()));
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


    public void dropDrinkOrFood(DragEvent event){
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

    public void dropSwitch(ImageView v, Context context){
        switch (v.getId()){
            case R.id.melk :
                student.drinkMilk(context);
                break;

            case R.id.appel :
                student.eatApple(context);
                break;

            case R.id.peer :
                student.eatPear(context);
                break;

            case R.id.wortel :
                student.eatCarrot(context);
                break;

            default :
                student.eatBread(context);
                break;
        }
        student.updateProgressbars();
    }


}
