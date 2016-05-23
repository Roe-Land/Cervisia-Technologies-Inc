package com.example.dennis.studlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

    }

    public void newGame(){
        Intent intent = new Intent(this, ThuisActivity.class);
        startActivity(intent);
    }
}
