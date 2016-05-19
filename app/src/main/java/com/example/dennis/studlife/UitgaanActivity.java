package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class UitgaanActivity extends AppCompatActivity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitgaan);
        Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));

        student = new Student();
        student = student.get(this);

        ProgressBar vier = (ProgressBar) findViewById(R.id.progressBar4);
        ProgressBar vijf = (ProgressBar) findViewById(R.id.progressBar5);
        ProgressBar zes = (ProgressBar) findViewById(R.id.progressBar6);
        vier.setProgress(student.getEnergie());
        vijf.setProgress(student.getGeluk());
        zes.setProgress(student.getGezondheid());


    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}
