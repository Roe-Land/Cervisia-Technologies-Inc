package com.example.dennis.studlife;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dennis on 17-5-2016.
 */
public class Time{
    private static long time;
    private Student student;
    private int currentProgressbarEnergy = 0;
    private int currentProgressbarHealth = 0;
    private int currentProgressbarHappiness = 0;

    public Time(Student student){
        this.student = student;
    }

    public void updateTime(){
        time = System.currentTimeMillis();
        if (student.getStartedWithLife() == 0){
            student.setStartedWithLife(time);
        }
        makeProgressBarsValues();
    }

    public void makeProgressBarsValues(){
        boolean changed = false;
        int progressbarEnergy = (int) ((time - student.getStartedWithLife()) / student.getMsPerEnergy());
        int progressbarHealth = (int) ((time - student.getStartedWithLife()) / student.getMsPerHealth());
        int progressbarHappiness = (int) ((time - student.getStartedWithLife()) / student.getMsPerHappiness());
        if(currentProgressbarEnergy < progressbarEnergy){
            currentProgressbarEnergy = progressbarEnergy;
            student.setEnergyFromTime(currentProgressbarEnergy);
            changed = true;
        }
        if(currentProgressbarHealth < progressbarHealth){
            currentProgressbarHealth = progressbarHealth;
            student.setHealthFromTime(currentProgressbarHealth);
            changed = true;
        }
        if(currentProgressbarHappiness < progressbarHappiness){
            currentProgressbarHappiness = progressbarHappiness;
            student.setHappinessFromTime(currentProgressbarHappiness);
            changed = true;
        }
        if (changed){
            student.updateProgressbars();
        }
    }

}
