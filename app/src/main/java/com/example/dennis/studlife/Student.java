package com.example.dennis.studlife;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;


/**
 * Created by dennis on 18-5-2016.
 */
public class Student{
    private static final String PREFS_NAME = "MyPrefsFile7";
    private static final String PREFS_GEZONDHEID = "gezondheid";
    private static final String PREFS_GELUK = "geluk";
    private static final String PREFS_ENERGIE = "energie";
    private static final String PREFS_SOCIALEGOD = "socialeGod";
    private static final String PREFS_STUDIEVOORTGANG = "studieVoortgang";
    private static final String PREFS_STARTEDWITHLIFE = "startedWithLife";
    private static final String PREFS_HEALTHFROMTIME = "healthFromTime";
    private static final String PREFS_HAPPINESSFROMTIME = "happinessFromTime";
    private static final String PREFS_ENERGYFROMTIME = "energyFromTime";
    private static final String PREFS_HEALTHNOTFROMTIME = "healthNotFromTime";
    private static final String PREFS_HAPPINESSNOTFROMTIME = "happinessNotFromTime";
    private static final String PREFS_ENERGYNOTFROMTIME = "energyNotFromTime";
    private static final String PREFS_MONEY = "money";
    private static final String PREFS_MONEYSPEND = "moneySpend";
    private static final String PREFS_ISSTUDDYING = "isStuddying";
    private static final String PREFS_STOPSTUDDYING = "stopStuddying";
    private static final long msPerHealth = 360000;
    private static final long msPerHappiness = 300000;
    private static final long msPerEnergy = 480000;
    private static final long msPerStufi = 86400000;

    private int health, happiness, energy, socialeGod, studieVoortgang;
    private long startedWithLife = 0;

    private boolean isStuddying;
    private long stopStuddying;

    private int healthNotFromTime = 0;
    private int happinessNotFromTime = 0;
    private int energyNotFromTime = 0;
    private int healthFromTime = 0;
    private int happinessFromTime = 0;
    private int energyFromTime = 0;
    private float money;
    private float moneyFromStufi = 0;
    private float moneySpend;

    private int bierCounter = 0;

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


    public long getStartedWithLife(){
        return startedWithLife;
    }

    public float getMoney(){
        return money;
    }

    public boolean getIsStuddying(){
        return isStuddying;
    }

    public long getStopStuddying(){
        return stopStuddying;
    }

    public void setStopStuddying(long stopStuddying){
        this.stopStuddying = stopStuddying;
    }

    public void setIsStuddying(boolean isStuddying){
        this.isStuddying = isStuddying;
    }

    public void setMoneyFromStufi(float moneyFromStufi){
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

    /*public void setGezondheid(int g, Context context){
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
    }*/

    public void setSocialeGod(int s){
        socialeGod = s;
    }

    public void setStudieVoortgang(int s){
        studieVoortgang = s;
    }


    public void updateProgressbars(){
        setMax();
        health = MAX - healthFromTime - healthNotFromTime;
        happiness = MAX - happinessFromTime - happinessNotFromTime;
        energy = MAX - energyFromTime - energyNotFromTime;
        money = moneyFromStufi - moneySpend;
        checkDead((Context)context);
        context.setProgressBarsAndTextViewsValues();

    }



    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, happiness);
        editor.putInt(PREFS_GEZONDHEID, health);
        editor.putInt(PREFS_ENERGIE, energy);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        editor.putLong(PREFS_STARTEDWITHLIFE, startedWithLife);
        editor.putInt(PREFS_HEALTHFROMTIME, healthFromTime);
        editor.putInt(PREFS_HAPPINESSFROMTIME, happinessFromTime);
        editor.putInt(PREFS_ENERGYFROMTIME, energyFromTime);
        editor.putInt(PREFS_HEALTHNOTFROMTIME, healthNotFromTime);
        editor.putInt(PREFS_HAPPINESSNOTFROMTIME, happinessNotFromTime);
        editor.putInt(PREFS_ENERGYNOTFROMTIME, energyNotFromTime);
        editor.putFloat(PREFS_MONEY, money);
        editor.putFloat(PREFS_MONEYSPEND, moneySpend);
        editor.putBoolean(PREFS_ISSTUDDYING, isStuddying);
        editor.putLong(PREFS_STOPSTUDDYING, stopStuddying);
        editor.apply();
    }

    public void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREFS_GELUK);
        editor.remove(PREFS_GEZONDHEID);
        editor.remove(PREFS_ENERGIE);
        editor.remove(PREFS_SOCIALEGOD);
        editor.remove(PREFS_STUDIEVOORTGANG);
        editor.remove(PREFS_STARTEDWITHLIFE);
        editor.remove(PREFS_HEALTHFROMTIME);
        editor.remove(PREFS_HAPPINESSFROMTIME);
        editor.remove(PREFS_ENERGYFROMTIME);
        editor.remove(PREFS_HEALTHNOTFROMTIME);
        editor.remove(PREFS_HAPPINESSNOTFROMTIME);
        editor.remove(PREFS_ENERGYNOTFROMTIME);
        editor.remove(PREFS_MONEY);
        editor.remove(PREFS_MONEYSPEND);
        editor.remove(PREFS_ISSTUDDYING);
        editor.remove(PREFS_STOPSTUDDYING);
        //editor.clear();
        editor.apply();
    }

    public static Student get(Context context) {
        Student student = new Student();
        SharedPreferences barsAndTime = context.getSharedPreferences(PREFS_NAME, 0);
        student.health = barsAndTime.getInt(PREFS_GEZONDHEID, MAX);
        student.happiness = barsAndTime.getInt(PREFS_GELUK, MAX);
        student.energy = barsAndTime.getInt(PREFS_ENERGIE, MAX);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, MIN);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, MIN);
        student.startedWithLife = barsAndTime.getLong(PREFS_STARTEDWITHLIFE, System.currentTimeMillis());
        student.healthFromTime = barsAndTime.getInt(PREFS_HEALTHFROMTIME, MIN);
        student.happinessFromTime = barsAndTime.getInt(PREFS_HAPPINESSFROMTIME, MIN);
        student.energyFromTime = barsAndTime.getInt(PREFS_ENERGYFROMTIME, MIN);
        student.money = barsAndTime.getFloat(PREFS_MONEY, MIN);
        student.moneySpend = barsAndTime.getFloat(PREFS_MONEYSPEND, MIN);
        student.isStuddying = barsAndTime.getBoolean(PREFS_ISSTUDDYING, false);
        student.stopStuddying = barsAndTime.getLong(PREFS_STOPSTUDDYING, MIN);
        student.healthNotFromTime = barsAndTime.getInt(PREFS_HEALTHNOTFROMTIME, MIN);
        student.happinessNotFromTime = barsAndTime.getInt(PREFS_HAPPINESSNOTFROMTIME, MIN);
        student.energyNotFromTime = barsAndTime.getInt(PREFS_ENERGYNOTFROMTIME , MIN);
        return student;
    }

    public void clickable(Spinner spinnerIntents, Spinner spinnerSleepOrStuddy){
        spinnerIntents.setClickable(true);
        spinnerSleepOrStuddy.setClickable(true);
    }

    public void checkDead(Context context){
        if((MAX - happinessFromTime - happinessNotFromTime <= MIN) || (MAX - healthFromTime - healthNotFromTime <= MIN) || (MAX - energyFromTime - energyNotFromTime <= MIN)){
            Intent intent = new Intent((Activity)context, GameOverActivity.class);
            context.startActivity(intent);
            timeRunner.stop();
            ((Activity)context).finish();
        }
    }

    private void setMax(){
        if (MAX - healthFromTime - healthNotFromTime > MAX){
            healthNotFromTime = healthNotFromTime + ((-healthNotFromTime) - healthFromTime);
        }
        if (MAX - happinessFromTime - happinessNotFromTime > MAX){
            happinessNotFromTime = happinessNotFromTime + ((-happinessNotFromTime) - happinessFromTime);
        }
        if(MAX - energyFromTime - energyNotFromTime > MAX){
            energyNotFromTime = energyNotFromTime + ((-energyNotFromTime) - energyFromTime);
        }
    }

    public void makeTimeThread() {
        Time time = new Time();
        timeRunner = new TimeRunner(time);
        Thread thread = new Thread(timeRunner);
        thread.start();
    }

    public void eatToeter(Context context){
        float price = (float) 5;
        if(checkMoney(price, context)) {
            healthNotFromTime += 8;
            happinessNotFromTime -= 5;
            energyNotFromTime -= 1;
            moneySpend += price;
        }
    }

    public void drinkBeer(Context context){
        float price = (float) 1.5;
        if(checkMoney(price, context)) {
            healthNotFromTime += 5;
            happinessNotFromTime -= 6;
            energyNotFromTime += 1;
            moneySpend += price;
            bierCounter++;
        }
    }

    public void eatHamburger(Context context){
        float price = (float) 1;
        if(checkMoney(price, context)) {
            healthNotFromTime += 5;
            happinessNotFromTime -= 2;
            energyNotFromTime -= 0;
            moneySpend += price;
        }
    }

    public void eatPizza(Context context){
        float price = (float) 5;
        if(checkMoney(price, context)) {
            healthNotFromTime += 6;
            happinessNotFromTime -= 4;
            energyNotFromTime -= 1;
            moneySpend += price;
        }
    }

    public void eatFries(Context context){
        float price = (float) 1.5;
        if(checkMoney(price, context)) {
            healthNotFromTime += 4;
            happinessNotFromTime -= 2;
            energyNotFromTime -= 0;
            moneySpend += price;
        }
    }

    public void eatBread(Context context){
        float price = (float) 0.2;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 2;
            happinessNotFromTime += 0;
            energyNotFromTime -= 3;
            moneySpend += price;
        }
    }

    public void drinkMilk(Context context){
        float price = (float) 0.5;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 4;
            happinessNotFromTime += 2;
            energyNotFromTime -= 2;
            moneySpend += price;
        }
    }

    public void eatCarrot(Context context){
        float price = (float) 0.5;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 5;
            happinessNotFromTime += 5;
            energyNotFromTime -= 4;
            moneySpend += price;
        }
    }

    public void eatPear(Context context){
        float price = (float) 0.25;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 4;
            happinessNotFromTime += 1;
            energyNotFromTime -= 3;
            moneySpend += price;
        }
    }

    public void eatApple(Context context){
        float price = (float) 0.25;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 3;
            happinessNotFromTime -= 0;
            energyNotFromTime -= 3;
            moneySpend += price;
        }
    }

    public void drinkEnergy(Context context){
        float price = (float) 0.5;
        if(checkMoney(price, context)) {
            healthNotFromTime += 4;
            happinessNotFromTime -= 2;
            energyNotFromTime -= 8;
            moneySpend += price;
        }
    }

    public void drinkWater(Context context){
        float price = (float) 0.1;
        if(checkMoney(price, context)) {
            healthNotFromTime -= 2;
            happinessNotFromTime -= 0;
            energyNotFromTime -= 1;
            moneySpend += price;
        }
    }

    public void drinkCola(Context context){
        float price = (float) 0.5;
        if(checkMoney(price , context)) {
            healthNotFromTime += 3;
            happinessNotFromTime -= 2;
            energyNotFromTime -= 1;
            moneySpend += price;
        }
    }

    public void eatCrisps(Context context){
        float price = (float) 1;
        if(checkMoney(price, context)) {
            healthNotFromTime += 4;
            happinessNotFromTime -= 3;
            energyNotFromTime -= 0;
            moneySpend += price;
        }
    }

    public boolean checkMoney (float price, Context context)
    {
        if(money - price < 0){
            Toast.makeText(context, "Niet genoeg geld, skeere tata", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
}
