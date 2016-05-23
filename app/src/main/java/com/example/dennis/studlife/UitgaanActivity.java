package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.view.View;

public class UitgaanActivity extends AppCompatActivity {
    private Student student;
    private ProgressBar vier;
    private ProgressBar vijf;
    private ProgressBar zes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitgaan);
        Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        student = student.get(this);
        student.checkDead(this);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this, student));
        vier = (ProgressBar) findViewById(R.id.progressBar4);
        vijf = (ProgressBar) findViewById(R.id.progressBar5);
        zes = (ProgressBar) findViewById(R.id.progressBar6);
        vier.setProgress(student.getEnergie());
        vijf.setProgress(student.getGeluk());
        zes.setProgress(student.getGezondheid());


    }

    public void drinkBier(View v){
        int u = student.getGezondheid();
        zes.setProgress(u - 6);
        student.setGezondheid( u - 6, this);

        int w = student.getGeluk();
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
