package com.example.dennis.studlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import java.io.Serializable;

public class GameOverActivity extends AppCompatActivity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

    }

    public void newGame(View v){
        Intent stud = getIntent();
        student = (Student) stud.getSerializableExtra("student");
        student.clear(this);
        student = student.get(this);
        Intent intent = new Intent(this, ThuisActivity.class);
        intent.putExtra("student", student);
        startActivity(intent);
        finish();
    }
}
