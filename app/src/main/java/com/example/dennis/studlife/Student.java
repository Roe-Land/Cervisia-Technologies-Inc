package com.example.dennis.studlife;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.DragEvent;
import android.widget.Toast;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Student class. The main class for our stud.
 * Mostly holds parameters used for storing the various attributes of our stud.
 */
public class Student{
    private static final String PREFS_NAME = "MyPrefsFile11";
    private static final String PREFS_GEZONDHEID = "gezondheid";
    private static final String PREFS_GELUK = "geluk";
    private static final String PREFS_ENERGIE = "energie";
    private static final String PREFS_SOCIALGOD = "socialGod";
    private static final String PREFS_STUDYPROGRESS = "studyProgress";
    private static final String PREFS_STARTEDWITHLIFE = "startedWithLife";
    private static final String PREFS_HEALTHFROMTIME = "healthFromTime";
    private static final String PREFS_HAPPINESSFROMTIME = "happinessFromTime";
    private static final String PREFS_ENERGYFROMTIME = "energyFromTime";
    private static final String PREFS_HEALTHNOTFROMTIME = "healthNotFromTime";
    private static final String PREFS_HAPPINESSNOTFROMTIME = "happinessNotFromTime";
    private static final String PREFS_ENERGYNOTFROMTIME = "energyNotFromTime";
    private static final String PREFS_MONEY = "money";
    private static final String PREFS_MONEYSPEND = "moneySpend";
    private static final String PREFS_ISSTUDYING = "isStudying";
    private static final String PREFS_STOPSTUDDYINGORSLEEPING = "stopStuddyingOrSleeping";
    private static final String PREFS_ISSLEEPING = "isSleeping";
    private static final long msPerHealth = 720000;
    private static final long msPerHappiness = 960000;
    private static final long msPerEnergy = 600000;
    private static final long msPerStufi = 86400000;

    private int health, happiness, energy, socialGod, studyProgress;
    private long startedWithLife = 0;

    private boolean isStudying;
    private boolean isSleeping;
    private long stopStuddyingOrSleeping;

    private int healthNotFromTime = 0;
    private int healthFromTime = 0;

    private int happinessNotFromTime = 0;
    private int happinessFromTime = 0;

    private int energyNotFromTime = 0;
    private int energyFromTime = 0;

    private float money;
    private float moneyFromStufi = 0;
    private float moneySpend;

    private int bierCounter = 0;

    private TimeRunner timeRunner;
    private Activitys context;

    private static final int MAX = 100;
    private static final int MIN = 0;

    /**
     * Below are all getters and setters used for getting and setting various stats of our stud.
     * @return
     */
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

    public int getSocialGod(){
        return socialGod;
    }

    public int getStudyProgress(){
        return  studyProgress;
    }

    public long getStartedWithLife(){
        return startedWithLife;
    }

    public float getMoney(){
        return money;
    }

    public boolean getIsStudying(){
        return isStudying;
    }

    public long getStopStuddyingOrSleeping(){
        return stopStuddyingOrSleeping;
    }

    public int getBierCounter(){
        return bierCounter;
    }

    public float getMoneyFromStufi(){
        return moneyFromStufi;
    }

    public int getHealthNotFromTime(){
        return healthNotFromTime;
    }

    public int getEnergyNotFromTime(){
        return energyNotFromTime;
    }

    public int getHappinessNotFromTime(){
        return happinessNotFromTime;
    }

    public float getMoneySpend(){
        return moneySpend;
    }

    public boolean getIsSleeping(){
        return isSleeping;
    }


    public void setIsSleeping(boolean isSleeping){
        this.isSleeping = isSleeping;
    }

    public void setHappinessNotFromTime(int happinessNotFromTime){
        this.happinessNotFromTime = happinessNotFromTime;
    }

    public void setEnergyNotFromTime( int energyNotFromTime){
        this.energyNotFromTime = energyNotFromTime;
    }

    public void setHealthNotFromTime(int healthNotFromTime){
        this.healthNotFromTime = healthNotFromTime;
    }

    public void setBierCounter(int bier){
        this.bierCounter = bier;
    }

    public void setStopStuddyingOrSleeping(long stopStuddyingOrSleeping){
        this.stopStuddyingOrSleeping = stopStuddyingOrSleeping;
    }

    public void setIsStudying(boolean isStudying){
        this.isStudying = isStudying;
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

    public void setStudyProgress(int studyProgress){
        this.studyProgress = studyProgress;
    }

    /**
     * Calculates the health, happiness and energy by the max minus the health, happiness and energy
     * from the events or other things minus the health, happiness and energy which comes
     * from the time.
     * Calculates the total money by the money gets from stufi minus money spend.
     * After this, health, happiness, energy and money get updated.
     */
    public void updateProgressbars(){
        setMax();
        health = MAX - healthFromTime - healthNotFromTime;
        happiness = MAX - happinessFromTime - happinessNotFromTime;
        energy = MAX - energyFromTime - energyNotFromTime;
        money = moneyFromStufi - moneySpend;
        checkDead((Context)context);
        context.setProgressBarsAndTextViewsValues();
    }

    /**
     * Method used for saving the current state of the app and stud.
     * @param context
     */
    public void save(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, happiness);
        editor.putInt(PREFS_GEZONDHEID, health);
        editor.putInt(PREFS_ENERGIE, energy);
        editor.putInt(PREFS_SOCIALGOD, socialGod);
        editor.putInt(PREFS_STUDYPROGRESS, studyProgress);
        editor.putLong(PREFS_STARTEDWITHLIFE, startedWithLife);
        editor.putInt(PREFS_HEALTHFROMTIME, healthFromTime);
        editor.putInt(PREFS_HAPPINESSFROMTIME, happinessFromTime);
        editor.putInt(PREFS_ENERGYFROMTIME, energyFromTime);
        editor.putInt(PREFS_HEALTHNOTFROMTIME, healthNotFromTime);
        editor.putInt(PREFS_HAPPINESSNOTFROMTIME, happinessNotFromTime);
        editor.putInt(PREFS_ENERGYNOTFROMTIME, energyNotFromTime);
        editor.putFloat(PREFS_MONEY, money);
        editor.putFloat(PREFS_MONEYSPEND, moneySpend);
        editor.putBoolean(PREFS_ISSTUDYING, isStudying);
        editor.putLong(PREFS_STOPSTUDDYINGORSLEEPING, stopStuddyingOrSleeping);
        editor.putBoolean(PREFS_ISSLEEPING, isSleeping);
        editor.apply();
    }

    /**
     * Clears all the stats. Used when a new game is started after stud's death.
     * @param context
     */
    public void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*
        editor.remove(PREFS_GELUK);
        editor.remove(PREFS_GEZONDHEID);
        editor.remove(PREFS_ENERGIE);
        editor.remove(PREFS_SOCIALGOD);
        editor.remove(PREFS_STUDYPROGRESS);
        editor.remove(PREFS_STARTEDWITHLIFE);
        editor.remove(PREFS_HEALTHFROMTIME);
        editor.remove(PREFS_HAPPINESSFROMTIME);
        editor.remove(PREFS_ENERGYFROMTIME);
        editor.remove(PREFS_HEALTHNOTFROMTIME);
        editor.remove(PREFS_HAPPINESSNOTFROMTIME);
        editor.remove(PREFS_ENERGYNOTFROMTIME);
        editor.remove(PREFS_MONEY);
        editor.remove(PREFS_MONEYSPEND);
        editor.remove(PREFS_ISSTUDYING);
        editor.remove(PREFS_STOPSTUDDYINGORSLEEPING);
        editor.remove(PREFS_ISSLEEPING);
        */
        editor.clear();
        editor.apply();
    }

    /**
     * Constructor for our stud. Initialising all his stats to the default values.
     * @param context
     * @return
     */
    public static Student get(Context context) {
        Student student = new Student();
        SharedPreferences barsAndTime = context.getSharedPreferences(PREFS_NAME, 0);
        student.health = barsAndTime.getInt(PREFS_GEZONDHEID, MAX);
        student.happiness = barsAndTime.getInt(PREFS_GELUK, MAX);
        student.energy = barsAndTime.getInt(PREFS_ENERGIE, MAX);
        student.socialGod = barsAndTime.getInt(PREFS_SOCIALGOD, MIN);
        student.studyProgress = barsAndTime.getInt(PREFS_STUDYPROGRESS, MIN);
        student.startedWithLife = barsAndTime.getLong(PREFS_STARTEDWITHLIFE, System.currentTimeMillis());
        student.healthFromTime = barsAndTime.getInt(PREFS_HEALTHFROMTIME, MIN);
        student.happinessFromTime = barsAndTime.getInt(PREFS_HAPPINESSFROMTIME, MIN);
        student.energyFromTime = barsAndTime.getInt(PREFS_ENERGYFROMTIME, MIN);
        student.money = barsAndTime.getFloat(PREFS_MONEY, MIN);
        student.moneySpend = barsAndTime.getFloat(PREFS_MONEYSPEND, MIN);
        student.isStudying = barsAndTime.getBoolean(PREFS_ISSTUDYING, false);
        student.stopStuddyingOrSleeping = barsAndTime.getLong(PREFS_STOPSTUDDYINGORSLEEPING, MIN);
        student.healthNotFromTime = barsAndTime.getInt(PREFS_HEALTHNOTFROMTIME, MIN);
        student.happinessNotFromTime = barsAndTime.getInt(PREFS_HAPPINESSNOTFROMTIME, MIN);
        student.energyNotFromTime = barsAndTime.getInt(PREFS_ENERGYNOTFROMTIME , MIN);
        student.isSleeping = barsAndTime.getBoolean(PREFS_ISSLEEPING, false);
        return student;
    }


    /**
     * Checks if the student is dead. Does this by looking at his stats.
     * @param context
     */
    public void checkDead(Context context){
        if((MAX - happinessFromTime - happinessNotFromTime <= MIN) || (MAX - healthFromTime - healthNotFromTime <= MIN) || (MAX - energyFromTime - energyNotFromTime <= MIN)){
            Intent intent = new Intent(context, GameOverActivity.class);
            context.startActivity(intent);
            timeRunner.stop();
            ((Activity)context).finish();
        }
    }

    /**
     * Adjusts the stats if they are above what the progressbars can hold.
     * Not visible to the user, but is necessary to make sure the game is played fair and acts logically.
     */
    public void setMax(){
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

    /**
     * Creates the time thread.
     */
    public void makeTimeThread() {
        Time time = new Time();
        timeRunner = new TimeRunner(time);
        Thread thread = new Thread(timeRunner);
        thread.start();
    }

    /**
     * Enables our stud to consume various goods, depending on the location where he's at.
     * If he drinks beer, this is counted for the scripted events.
     * @param context
     * @param health
     * @param happiness
     * @param energy
     * @param price
     * @param bier
     * @param event
     */
    public void eatOrDrink(Context context,int health, int happiness, int energy, float price, boolean bier, DragEvent event){
        if(checkMoney(price, context)) {
            ((Activitys)context).dropDrinkOrFood(event);
            healthNotFromTime += health;
            happinessNotFromTime += happiness;
            energyNotFromTime += energy;
            moneySpend += price;
            if(bier){
                bierCounter++;
            }
            updateProgressbars();
        }
    }

    /**
     * Our stud initiates a random event, and the stats update accordingy to the outcome.
     * @param health
     * @param happiness
     * @param socialGod
     * @param money
     */
    public void doEvent(int health, int happiness, int socialGod, float money){
        healthNotFromTime += health;
        happinessNotFromTime += happiness;
        this.socialGod += socialGod;
        moneySpend += money;
        ((UitgaanActivity)context).updateSocialStudy();
        updateProgressbars();
    }


    /**
     * Checks if the amount of money the stud has is enough for what he wants to do.
     * @param price
     * @param context
     * @return
     */
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


    /**
     * Super secret, and kinda random, but still honest and consistent, formula to calculate a highscore.
     * @return
     */
    public int calculateScores(){
        if (health < 0){
            health = 0;
        }
        if(happiness < 0){
            happiness = 0;
        }
        if(energy < 0){
            energy = 0;
        }
        int score = 0;
        score += (socialGod * 120);
        score += (studyProgress * 100);
        score += (health * 11);
        score += (happiness * 7);
        score += (energy * 6);
        return score;
    }
}
