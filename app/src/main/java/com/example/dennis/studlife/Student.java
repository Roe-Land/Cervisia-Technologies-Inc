package com.example.dennis.studlife;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by dennis on 18-5-2016.
 */
public class Student {
    private static final String PREFS_NAME = "prefs";
    private static final String PREFS_GEZONDHEID = "gezondheid";
    private static final String PREFS_GELUK = "geluk";
    private static final String PREFS_ENERGIE = "energie";
    private static final String PREFS_SOCIALEGOD = "socialeGod";
    private static final String PREFS_STUDIEVOORTGANG = "studieVoortgang";

    private int gezondheid;
    private int geluk;
    private int energie;
    private int socialeGod;
    private int studieVoortgang;

    private Student student;

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

    public void setGezondheid(int g){
        gezondheid = g;
    }

    public void setGeluk(int g){
        geluk = g;
    }

    public void setEnergie(int e){
        energie = e;
    }

    public void setSocialeGod(int s){
        socialeGod = s;
    }

    public void setStudieVoortgang(int s){
        studieVoortgang = s;
    }

    public void save(Context context) {
        // shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_GELUK, geluk);
        editor.putInt(PREFS_GEZONDHEID, gezondheid);
        editor.putInt(PREFS_ENERGIE, energie);
        editor.putInt(PREFS_SOCIALEGOD, socialeGod);
        editor.putInt(PREFS_STUDIEVOORTGANG, studieVoortgang);
        editor.commit();
    }

    public static Student get(Context context) {
        int i = 100;
        int j = 0;
        Student student = new Student();
        SharedPreferences barsAndTime = context.getSharedPreferences(PREFS_NAME, 0);
        student.gezondheid = barsAndTime.getInt(PREFS_GEZONDHEID, i);
        student.geluk = barsAndTime.getInt(PREFS_GELUK, i);
        student.energie = barsAndTime.getInt(PREFS_ENERGIE, i);
        student.socialeGod = barsAndTime.getInt(PREFS_SOCIALEGOD, j);
        student.studieVoortgang = barsAndTime.getInt(PREFS_STUDIEVOORTGANG, j);
        return student;
    }
}
