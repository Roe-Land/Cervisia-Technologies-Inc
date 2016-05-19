package com.example.dennis.studlife;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dennis on 17-5-2016.
 */
public class Time {
    private String startTime;
    private String time;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int seconds;


    public Time(){
        this.time = new SimpleDateFormat("yyyy MM dd HH mm ss").format(Calendar.getInstance().getTime());
        stringToInts(time);
    }

    public void stringToInts(String a){
        int[] time = {this.year, this.month, this.day, this.hour, this.minutes, this.seconds};
        StringBuilder build = new StringBuilder();
        int j = 0;
        for (int i = 0; i < a.length(); i++){
            char b = a.charAt(i);
            if (b != ' '){
                build.append(b);
            } else {
                time[j] = Integer.parseInt(build.toString());
                j++;
                build.delete(0, build.length());
            }
        }
    }

    public String intsToString(){
        return  (year + " " + month + " " + day + " " + hour + " " + minutes + " " + seconds);
    }
}
