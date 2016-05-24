package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by dennis on 18-5-2016.
 */
public class Student implements Serializable{
    private static final String PREFS_NAME = "prefs";
    private static final String PREFS_GEZONDHEID = "gezondheid";
    private static final String PREFS_GELUK = "geluk";
    private static final String PREFS_ENERGIE = "energie";
    private static final String PREFS_SOCIALEGOD = "socialeGod";
    private static final String PREFS_STUDIEVOORTGANG = "studieVoortgang";
    private static final String PREFS_ISDOODGEGAAN = "isDoodgegaan";

    private int gezondheid;
    private int geluk;
    private int energie;
    private int socialeGod;
    private int studieVoortgang;
    private boolean isDoodgegaan;



    public Student(){
        this.gezondheid = 100;
        this.geluk = 100;
        this.energie = 100;
        this.socialeGod = 0;
        this.studieVoortgang = 0;
    }

    public int getGezondheid(){
        return gezondheid;
    }

    public int getGeluk(){
        return geluk;
    }

    public int getEnergie(){
        return energie;
    }

    public int getSocialeGod(){
        return socialeGod;
    }

    public int getStudieVoortgang(){
        return  studieVoortgang;
    }

    public boolean getIsDoodgegaan(){
        return isDoodgegaan;
    }

    public void setGezondheid(int g, Context context){
        gezondheid = g;
        setMax();
        checkDead(context);
    }

    public void setGeluk(int g, Context context){
        geluk = g;
        setMax();
        checkDead(context);
    }

    public void setEnergie(int e, Context context){
        energie = e;
        setMax();
        checkDead(context);
    }

    public void setSocialeGod(int s){
        socialeGod = s;
    }

    public void setStudieVoortgang(int s){
        studieVoortgang = s;
    }

    public void setIsDoodgegaan(boolean a){
        this.isDoodgegaan = a;
    }


    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, geluk);
        editor.putInt(PREFS_GEZONDHEID, gezondheid);
        editor.putInt(PREFS_ENERGIE, energie);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        editor.putBoolean(PREFS_ISDOODGEGAAN, isDoodgegaan);
        editor.commit();
    }

    public void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, 100);
        editor.putInt(PREFS_GEZONDHEID, 100);
        editor.putInt(PREFS_ENERGIE, 100);
        editor.putInt(PREFS_SOCIALEGOD, 0);
        editor.putInt(PREFS_STUDIEVOORTGANG, 0);
        editor.commit();
    }



    public static Student get(Context context) {
        int i = 100;
        int j = 0;
        Student student = new Student();
        SharedPreferences barsAndTime = ((Activity)context).getSharedPreferences(PREFS_NAME, 0);
        student.gezondheid = barsAndTime.getInt(PREFS_GEZONDHEID, i);
        student.geluk = barsAndTime.getInt(PREFS_GELUK, i);
        student.energie = barsAndTime.getInt(PREFS_ENERGIE, i);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, j);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, j);
        student.isDoodgegaan = barsAndTime.getBoolean(PREFS_ISDOODGEGAAN, false);
        return student;
    }

    public void checkDead(Context context){
        if((geluk <= 0) || (gezondheid <= 0) || (energie <= 0)){
            Intent intent = new Intent((Activity)context, GameOverActivity.class);
            intent.putExtra("student", this);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    private void setMax(){
        if (gezondheid > 100){
            gezondheid = 100;
        }
        if (geluk > 100){
            geluk = 100;
        }
        if(energie > 100){
            energie = 100;
        }
    }
}
