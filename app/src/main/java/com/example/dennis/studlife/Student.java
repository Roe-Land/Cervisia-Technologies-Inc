package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ProgressBar;

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
    private static final String PREFS_STARTEDWITHLIFE = "startedWithLife";
    private static final String PREFS_HEALTHFROMTIME = "healthFromTime";
    private static final String PREFS_HAPPINESSFROMTIME = "happinessFromTime";
    private static final String PREFS_ENERGYFROMTIME = "energyFromTime";
    private static final long msPerHealth = 360000;
    private static final long msPerHappiness = 300000;
    private static final long msPerEnergy = 480000;

    private int health, happiness, energy, socialeGod, studieVoortgang;
    private boolean isDoodgegaan;
    private long startedWithLife = 0;


    private int healthNotFromTime = 0;
    private int happinessNotFromTime = 0;
    private int energyNotFromTime = 0;
    private int healthFromTime = 0;
    private int happinessFromTime = 0;
    private int energyFromTime;

    private ProgressBar barHealth, barHappiness, barEnergy;

    private static final int MAX = 100;
    private static final int MIN = 0;

    public long getMsPerHealth(){
        return msPerHealth;
    }

    public long getMsPerEnergy(){
        return msPerEnergy;
    }

    public long getMsPerHappiness(){
        return msPerHappiness;
    }

    public int getHealth(){
        return health;
    }

    public int getHappiness(){
        return happiness;
    }

    public int getEnergy(){
        return energy;
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

    public long getStartedWithLife(){
        return startedWithLife;
    }

    public void setProgressBars(ProgressBar health, ProgressBar happiness, ProgressBar energy){
        barHealth = health;
        barHappiness = happiness;
        barEnergy = energy;
    }


    public void setHealthFromTime(int healthFromTime){
        this.healthFromTime = healthFromTime;
    }

    public void setEnergyFromTime(int energyFromTime){
        this.energyFromTime = energyFromTime;
    }

    public void setHappinessFromTime(int happinessFromTime){
        this.happinessFromTime = happinessFromTime;
    }

    public void setStartedWithLife(long startedWithLife){
        this.startedWithLife = startedWithLife;
    }

    public void setGezondheid(int g, Context context){
        health = g;
        setMax();
        checkDead(context);
    }

    public void setGeluk(int g, Context context){
        happiness = g;
        setMax();
        checkDead(context);
    }

    public void setEnergie(int e, Context context){
        energy = e;
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


    public void updateProgressbars(){
        if (barHealth != null){
            barHealth.setProgress(MAX - healthFromTime - healthNotFromTime);
            barHappiness.setProgress(MAX - happinessFromTime - happinessNotFromTime);
            barEnergy.setProgress(MAX - energyFromTime - energyNotFromTime);
        }
    }



    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, happiness);
        editor.putInt(PREFS_GEZONDHEID, health);
        editor.putInt(PREFS_ENERGIE, energy);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        editor.putBoolean(PREFS_ISDOODGEGAAN, isDoodgegaan);
        editor.putLong(PREFS_STARTEDWITHLIFE, startedWithLife);
        editor.putInt(PREFS_HEALTHFROMTIME, healthFromTime);
        editor.putInt(PREFS_HAPPINESSFROMTIME, happinessFromTime);
        editor.putInt(PREFS_ENERGYFROMTIME, energyFromTime);
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
        editor.putLong(PREFS_STARTEDWITHLIFE, MIN);
        editor.putInt(PREFS_HEALTHFROMTIME, MIN);
        editor.putInt(PREFS_HAPPINESSFROMTIME, MIN);
        editor.putInt(PREFS_ENERGYFROMTIME, MIN);
        editor.commit();
    }

    public static Student get(Context context) {
        Student student = new Student();
        SharedPreferences barsAndTime = ((Activity)context).getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        student.health = barsAndTime.getInt(PREFS_GEZONDHEID, MAX);
        student.happiness = barsAndTime.getInt(PREFS_GELUK, MAX);
        student.energy = barsAndTime.getInt(PREFS_ENERGIE, MAX);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, MIN);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, MIN);
        student.isDoodgegaan = barsAndTime.getBoolean(PREFS_ISDOODGEGAAN, false);
        student.startedWithLife = barsAndTime.getLong(PREFS_STARTEDWITHLIFE, System.currentTimeMillis());
        student.healthFromTime = barsAndTime.getInt(PREFS_HEALTHFROMTIME, MIN);
        student.happinessFromTime = barsAndTime.getInt(PREFS_HAPPINESSFROMTIME, MIN);
        student.energyFromTime = barsAndTime.getInt(PREFS_ENERGYFROMTIME, MIN);
        return student;
    }

    public void checkDead(Context context){
        if((happiness <= MIN) || (health <= MIN) || (energy <= MIN)){
            Intent intent = new Intent((Activity)context, GameOverActivity.class);
            intent.putExtra("student", this);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    private void setMax(){
        if (health > MAX){
            health = MAX;
        }
        if (happiness > MAX){
            happiness = MAX;
        }
        if(energy > MAX){
            energy = MAX;
        }
    }
}
