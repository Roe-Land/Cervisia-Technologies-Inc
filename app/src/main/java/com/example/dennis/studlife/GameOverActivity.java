package com.example.dennis.studlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.Serializable;

public class GameOverActivity extends AppCompatActivity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        student = ApplicationClass.student;
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        setBackground(layout);
    }

    public void setBackground(RelativeLayout layout){
        int studiepunten = student.getStudieVoortgang();
        int socialeGod = student.getSocialeGod();
        if ((studiepunten < (socialeGod + 10)) && (studiepunten > (socialeGod - 10))){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenbalans));
        }else if((socialeGod < 20) && (studiepunten < 20)){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenniks));
        }else if(socialeGod < studiepunten){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenstudie));
        }else{
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdensociaal));
        }
    }

    public void newGame(View v){
        Intent stud = getIntent();
        //student.setIsDead(true);
        student.clear(this);
        ApplicationClass.student = student.get(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
