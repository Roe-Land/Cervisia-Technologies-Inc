package com.example.dennis.studlife;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private Student student;
    private RelativeLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu = (RelativeLayout) findViewById(R.id.layout);

        menu.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) menu.getBackground()).start();
            }
        });

    }

    public void viewHighscores(View v){

    }

    public void toGame(View v) {
        ApplicationClass.student = student.get(this);
        student = ApplicationClass.student;
        student.makeTimeThread();

        Intent intent = new Intent(this, ThuisActivity.class );
        startActivity(intent);
    }


}
