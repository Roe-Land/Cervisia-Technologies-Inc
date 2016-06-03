package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;

public class UitgaanActivity extends AppCompatActivity implements Activitys {
    private Student student;
    private ProgressBar vier;
    private ProgressBar vijf;
    private ProgressBar zes;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitgaan);
        student = ApplicationClass.student;
        student.checkDead(this);

        setProgressBarsAndTextViews();
        student.setClass(this);

        spinner = (Spinner) findViewById(R.id.spinner3);
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
        vier = (ProgressBar) findViewById(R.id.progressBar4);
        vijf = (ProgressBar) findViewById(R.id.progressBar5);
        zes = (ProgressBar) findViewById(R.id.progressBar6);
        vier.setProgress(student.getEnergy());
        vijf.setProgress(student.getHappiness());
        zes.setProgress(student.getHealth());
        money = (TextView) findViewById(R.id.money1);
        money.setText(student.getMoney() + " Studs");
        social = (TextView) findViewById(R.id.socialPoints);
        social.setText("Social: " + student.getSocialeGod());
        study = (TextView) findViewById(R.id.studyPoints);
        study.setText("Study: " + student.getStudieVoortgang());
    }

    @Override
    public void setProgressBarsAndTextViewsValues() {
        vier.setProgress(student.getEnergy());
        vijf.setProgress(student.getHappiness());
        zes.setProgress(student.getHealth());
        uiHandler.sendMessage(uiHandler.obtainMessage(student.getMoney()));
    }

    public void drinkBier(View v){
        int u = student.getHealth();
        zes.setProgress(u - 6);
        student.setGezondheid( u - 6, this);

        int w = student.getHappiness();
        vijf.setProgress(w + 8);
        student.setGeluk(w + 8, this);
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
