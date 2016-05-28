package com.example.dennis.studlife;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dennis on 17-5-2016.
 */
public class Time{
    public static String appCloses;
    private static String appStartedAgain;
    private static String time;
    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int minutes;
    private static int seconds;

    public void updateTime(){
        time = new SimpleDateFormat("yyyy MM dd HH mm ss").format(Calendar.getInstance().getTime());
        stringToInts(time);
    }

    public static void setAppStartedAgain(String i){
        appStartedAgain = i;
    }

    public static String getAppStartedAgain(){
        return appStartedAgain;
    }

    public static void setAppCloses(String i){
        appCloses = i;
    }

    public static void stringToInts(String a){
        StringBuilder build = new StringBuilder();
        int j = 0;
        for (int i = 0; i < a.length(); i++){
            char b = a.charAt(i);
            if (b != ' '){
                build.append(b);
            } else {
                int c = Integer.parseInt(build.toString());
                setDates(j, c);
                j++;
                build.delete(0, build.length());
            }
        }
        int d = Integer.parseInt(build.toString());
        setDates(j, d);
    }

    public static void setDates(int b, int c){
        switch (b){
            case 0 : year = c; break;
            case 1 : month = c; break;
            case 2 : day = c; break;
            case 3 : hour = c; break;
            case 4 : minutes = c; break;
            case 5 : seconds = c; break;
        }
    }

    public int secondsSinceLastPlay(){
        int i = 0;

        return i;
    }

    public static String intsToString(){
        return  (year + " " + month + " " + day + " " + hour + " " + minutes + " " + seconds);
    }
}
