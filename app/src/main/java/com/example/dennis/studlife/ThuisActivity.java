package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

public class ThuisActivity extends AppCompatActivity{

    private ImageView stud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener( new CustomOnItemSelectedListener(this));

        stud = (ImageView) findViewById(R.id.studanimation);

        stud.post(new Runnable() {

            @Override
            public void run() {
                ((AnimationDrawable) stud.getBackground()).start();
            }

        });
    }


    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    //@Override
    /*public void onBackPressed(){
        finishAffinity();
    }*/

}
