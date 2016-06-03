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
    private int currentMoneyGiven = 0;

    //public Time(Student student){
    //    this.student = student;
    //}

    public void updateTime(){
        student = ApplicationClass.student;
        time = System.currentTimeMillis();
        makeProgressBarsValues();
    }

    public void makeProgressBarsValues(){
        int progressbarEnergy = (int) ((time - student.getStartedWithLife()) / student.getMsPerEnergy());
        int progressbarHealth = (int) ((time - student.getStartedWithLife()) / student.getMsPerHealth());
        int progressbarHappiness = (int) ((time - student.getStartedWithLife()) / student.getMsPerHappiness());
        int money = (int) ((((time - student.getStartedWithLife()) / student.getMsPerStufi())+ 1)*100);
        boolean changed = testForUpdate(progressbarEnergy, progressbarHealth, progressbarHappiness, money);

        if (changed){
            if(student.getContext() != null) {
                student.updateProgressbars();
            }
        }
    }

    public boolean testForUpdate(int progressbarEnergy, int progressbarHealth, int progressbarHappiness, int money){
        boolean changed = false;
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
        if(currentMoneyGiven < money){
            currentMoneyGiven = money;
            student.setMoneyFromStufi(currentMoneyGiven);
            changed = true;
        }
        return changed;
    }

}
