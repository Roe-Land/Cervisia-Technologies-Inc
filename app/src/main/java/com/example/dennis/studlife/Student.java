package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

import java.io.Serializable;
import java.util.Observer;

/**
 * Created by dennis on 18-5-2016.
 */
public class Student implements Serializable{
    private static final String PREFS_GEZONDHEID = "gezondheid";
    private static final String PREFS_GELUK = "geluk";
    private static final String PREFS_ENERGIE = "energie";
    private static final String PREFS_SOCIALEGOD = "socialeGod";
    private static final String PREFS_STUDIEVOORTGANG = "studieVoortgang";
    private static final String PREFS_ISDOODGEGAAN = "isDoodgegaan";
    private static final String PREFS_APPCLOSES = "appCloses";

    private int gezondheid;
    private int geluk;
    private int energie;
    private int socialeGod;
    private int studieVoortgang;
    private boolean isDoodgegaan;

    private static final int MAX = 100;
    private static final int MIN = 0;

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

    public void newValuesAfterStartUp(Context context){
        String started = Time.getAppStartedAgain();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        String appClosed = sharedPreferences.getString(PREFS_APPCLOSES, Time.getAppStartedAgain());
    }

    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, geluk);
        editor.putInt(PREFS_GEZONDHEID, gezondheid);
        editor.putInt(PREFS_ENERGIE, energie);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        editor.putBoolean(PREFS_ISDOODGEGAAN, isDoodgegaan);
        editor.putString(PREFS_APPCLOSES, Time.appCloses);
        editor.commit();
    }

    public void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, MAX);
        editor.putInt(PREFS_GEZONDHEID, MAX);
        editor.putInt(PREFS_ENERGIE, MAX);
        editor.putInt(PREFS_SOCIALEGOD, MIN);
        editor.putInt(PREFS_STUDIEVOORTGANG, MIN);
        editor.commit();
    }

    public static Student get(Context context) {
        Student student = new Student();
        SharedPreferences barsAndTime = ((Activity)context).getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        student.gezondheid = barsAndTime.getInt(PREFS_GEZONDHEID, MAX);
        student.geluk = barsAndTime.getInt(PREFS_GELUK, MAX);
        student.energie = barsAndTime.getInt(PREFS_ENERGIE, MAX);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, MIN);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, MIN);
        student.isDoodgegaan = barsAndTime.getBoolean(PREFS_ISDOODGEGAAN, false);
        return student;
    }

    public void checkDead(Context context){
        if((geluk <= MIN) || (gezondheid <= MIN) || (energie <= MIN)){
            Intent intent = new Intent((Activity)context, GameOverActivity.class);
            intent.putExtra("student", this);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    private void setMax(){
        if (gezondheid > MAX){
            gezondheid = MAX;
        }
        if (geluk > MAX){
            geluk = MAX;
        }
        if(energie > MAX){
            energie = MAX;
        }
    }
}
