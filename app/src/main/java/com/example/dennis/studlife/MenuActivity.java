package com.example.dennis.studlife;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity {
    private Student student;
    private RelativeLayout menu;
    private AnimationDrawable animation;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu = (RelativeLayout) findViewById(R.id.layout);

        menu.post(new Runnable(){
            @Override
            public void run(){
                animation = ((AnimationDrawable) menu.getBackground());
                animation.start();
            }
        });

    }

    public void viewHighscores(View v){
        //intent = new Intent(this, Highscores.class);
        //startActivity(intent);

    }

    public void toGame(View v) {
        animation.stop();
        ApplicationClass.student = student.get(this);
        student = ApplicationClass.student;
        student.makeTimeThread();
        if(student.getIsStuddying() == true) {
            intent = new Intent(this, CollegeActivity.class);
        } else {
            intent = new Intent(this, ThuisActivity.class);
        }
        startActivity(intent);
        finish();
    }


}
