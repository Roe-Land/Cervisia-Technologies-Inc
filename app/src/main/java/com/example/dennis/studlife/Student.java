package com.example.dennis.studlife;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.io.Serializable;


/**
 * Created by dennis on 18-5-2016.
 */
public class Student{
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
    private static final String PREFS_MONEY = "money";
    private static final String PREFS_MONEYSPEND = "moneySpend";
    private static final long msPerHealth = 360000;
    private static final long msPerHappiness = 300000;
    private static final long msPerEnergy = 480000;
    private static final long msPerStufi = 86400000;

    private int health, happiness, energy, socialeGod, studieVoortgang, money;
    //private boolean isDead;
    private long startedWithLife = 0;


    private int healthNotFromTime = 0;
    private int happinessNotFromTime = 0;
    private int energyNotFromTime = 0;
    private int healthFromTime = 0;
    private int happinessFromTime = 0;
    private int energyFromTime = 0;
    private int moneyFromStufi = 0;
    private int moneySpend = 0;

    private TimeRunner timeRunner;
    private Activitys context;

    private static final int MAX = 100;
    private static final int MIN = 0;

    public Activitys getContext(){
        return context;
    }

    public long getMsPerHealth(){
        return msPerHealth;
    }

    public long getMsPerEnergy(){
        return msPerEnergy;
    }

    public long getMsPerHappiness(){
        return msPerHappiness;
    }

    public long getMsPerStufi(){
        return msPerStufi;
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

    //public boolean getIsDead(){
    //    return isDead;
    //}

    public long getStartedWithLife(){
        return startedWithLife;
    }

    public int getMoney(){
        return money;
    }

    public void setMoneyFromStufi(int moneyFromStufi){
        this.moneyFromStufi = moneyFromStufi;
    }


    public void setClass(Activitys context){
        this.context = context;
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

    //public void setIsDead(boolean isDead){
    //    this.isDead = isDead;
    //}


    public void updateProgressbars(){
        health = MAX - healthFromTime - healthNotFromTime;
        happiness = MAX - happinessFromTime - happinessNotFromTime;
        energy = MAX - energyFromTime - energyNotFromTime;
        money = moneyFromStufi - moneySpend;
        checkDead((Context)context);

        /* debug*/
        //System.out.println("health: " + healthFromTime + "happiness: " + happinessFromTime + "energy: " + energyFromTime);
        //System.out.println("health: " + health + "happiness: " + happiness + "energy: " + energy);
        /* end debug*/
        context.setProgressBarsAndTextViewsValues();

    }



    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, happiness);
        editor.putInt(PREFS_GEZONDHEID, health);
        editor.putInt(PREFS_ENERGIE, energy);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        //editor.putBoolean(PREFS_ISDOODGEGAAN, isDead);
        editor.putLong(PREFS_STARTEDWITHLIFE, startedWithLife);
        editor.putInt(PREFS_HEALTHFROMTIME, healthFromTime);
        editor.putInt(PREFS_HAPPINESSFROMTIME, happinessFromTime);
        editor.putInt(PREFS_ENERGYFROMTIME, energyFromTime);
        editor.putInt(PREFS_MONEY, money);
        editor.putInt(PREFS_MONEYSPEND, moneySpend);
        editor.commit();
    }

    public void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREFS_GELUK);
        editor.remove(PREFS_GEZONDHEID);
        editor.remove(PREFS_ENERGIE);
        editor.remove(PREFS_SOCIALEGOD);
        editor.remove(PREFS_STUDIEVOORTGANG);
        //editor.putBoolean(PREFS_ISDOODGEGAAN, isDead);
        editor.remove(PREFS_STARTEDWITHLIFE);
        editor.remove(PREFS_HEALTHFROMTIME);
        editor.remove(PREFS_HAPPINESSFROMTIME);
        editor.remove(PREFS_ENERGYFROMTIME);
        editor.remove(PREFS_MONEY);
        editor.remove(PREFS_MONEYSPEND);
        //editor.clear();
        editor.apply();
    }

    public static Student get(Context context) {
        Student student = new Student();
        SharedPreferences barsAndTime = context.getSharedPreferences(MenuActivity.PREFS_NAME, 0);
        student.health = barsAndTime.getInt(PREFS_GEZONDHEID, MAX);
        student.happiness = barsAndTime.getInt(PREFS_GELUK, MAX);
        student.energy = barsAndTime.getInt(PREFS_ENERGIE, MAX);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, MIN);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, MIN);
        //student.isDead = barsAndTime.getBoolean(PREFS_ISDOODGEGAAN, true);
        student.startedWithLife = barsAndTime.getLong(PREFS_STARTEDWITHLIFE, System.currentTimeMillis());
        student.healthFromTime = barsAndTime.getInt(PREFS_HEALTHFROMTIME, MIN);
        student.happinessFromTime = barsAndTime.getInt(PREFS_HAPPINESSFROMTIME, MIN);
        student.energyFromTime = barsAndTime.getInt(PREFS_ENERGYFROMTIME, MIN);
        student.money = barsAndTime.getInt(PREFS_MONEY, MIN);
        student.moneySpend = barsAndTime.getInt(PREFS_MONEYSPEND, MIN);
        return student;
    }

    public void checkDead(Context context){
        if((happiness <= MIN) || (health <= MIN) || (energy <= MIN)){
            Intent intent = new Intent((Activity)context, GameOverActivity.class);
            context.startActivity(intent);
            timeRunner.stop();
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

    public void makeTimeThread() {
        Time time = new Time();
        timeRunner = new TimeRunner(time);
        Thread thread = new Thread(timeRunner);
        thread.start();
    }
}
