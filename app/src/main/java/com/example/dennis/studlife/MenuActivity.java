package com.example.dennis.studlife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void viewHighscores(View v){

    }

    public void toGame(View v){
        student = student.get(this);

        makeTimeThread(student);

        Intent intent = new Intent(this, ThuisActivity.class );
        intent.putExtra("student", student);
        startActivity(intent);
    }

    public void makeTimeThread(Student student){
        Time time = new Time(student);
        TimeRunner timeRunner = new TimeRunner(time);
        Thread thread = new Thread(timeRunner);
        thread.start();
    }
}
