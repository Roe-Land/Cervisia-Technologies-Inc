package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CollegeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
    }

    public void uitgaan(){
        Intent intent = new Intent(this, UitgaanActivity.class);
        startActivity(intent);
        //YOLOLOLOL
    }

    public void goHome(){
        Intent intent = new Intent(this, ThuisActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}
