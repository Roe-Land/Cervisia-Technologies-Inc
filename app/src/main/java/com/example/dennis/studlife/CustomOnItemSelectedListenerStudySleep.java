package com.example.dennis.studlife;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * A custom OnItemSelectedListener. Used for sleeping and studying.
 */
public class CustomOnItemSelectedListenerStudySleep implements AdapterView.OnItemSelectedListener{
    private Student student;
    private String whatToDo;
    private Spinner spinner;
    private Spinner parent;
    private Context context;
    private ImageView stud;

    /**
     * Constructor. Sets everything to local variables, so they can be used.
     */
    public CustomOnItemSelectedListenerStudySleep(Student student, String whatToDo, Spinner spinner, Context context, ImageView stud){
        this.student = student;
        this.whatToDo = whatToDo;
        this.spinner = spinner;
        this.context = context;
        this.stud = stud;
    }

    /**
     * Defines what the the stud should do, and for how long. Depending on his location
     * studying or sleeping is made available. The time is in brackets of 20 minutes for studying
     * and 40 minutes for sleeping.
     * After chosing spinners are disabled.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.parent = (Spinner) parent;
        int minutes = position *20;
        long ms = minutes*60*1000;
        if (minutes > 0) {
            if (whatToDo == "study") {
                study(position, ms);
                disableSpinners();
            }
            if (whatToDo == "sleep") {
                sleep(position, ms * 2);
                disableSpinners();
            }
        }else{
            stillBusy();
        }
    }

    /**
     * Sets the stud to a sleeping or studying stud, and sets the stud again in sleep or study mode
     * when the app starts again and the stud has still to sleep or study.
     */
    public void stillBusy(){
        if(student.getIsSleeping()){
            spinner.setEnabled(false);
            this.parent.setEnabled(false);
            stud.getBackground().setVisible(false, false);
            stud.setBackgroundResource(R.drawable.stud_slapen);
            doNothing();
        }
        if(student.getIsStudying()){
            spinner.setEnabled(false);
            this.parent.setEnabled(false);
            doNothing();
        }
    }

    /**
     * Disables the spinners.
     */
    public void disableSpinners(){
        parent.setEnabled(false);
        spinner.setEnabled(false);
        student.updateProgressbars();
        doNothing();
    }

    /**
     * Sets the stud to sleep. For the chosen amount of time.
     * Also updates the stats with gained energy and health.
     * @param position
     * @param ms
     */
    public void sleep(int position, long ms){
        student.setStopStuddyingOrSleeping(Time.time + ms);
        student.setIsSleeping(true);
        ((AnimationDrawable) stud.getBackground()).stop();
        stud.setBackgroundResource(R.drawable.stud_slapen);
        student.setEnergyNotFromTime(student.getEnergyNotFromTime() - (position * 15));
        student.setHealthNotFromTime(student.getHealthNotFromTime() - (position * 5));
    }


    /**
     * Sets the stud to studying for the chosen amount of time.
     * Also updates the stats with lost energy and happiness, and gained study points.
     * @param position
     * @param ms
     */
    public void study(int position, long ms){
        student.setStopStuddyingOrSleeping(Time.time + ms);
        student.setIsStudying(true);
        student.setStudyProgress(student.getStudyProgress() + position);
        ((CollegeActivity)context).updateSocialStudy();
        student.setEnergyNotFromTime(student.getEnergyNotFromTime() + (position * 5));
        student.setHappinessNotFromTime(student.getHappinessNotFromTime() + (position * 7));
    }

    /**
     * Let's the thread wait out until the sign is given that our stud is done sleeping
     * or studying.
     */
    public void doNothing(){
        Runnable run = new Runnable(){
            @Override
            public void run(){
                while(student.getStopStuddyingOrSleeping() != 0) {
                    if (student.getStopStuddyingOrSleeping() <= Time.time) {
                        if(student.getIsStudying()) {
                            ((CollegeActivity) context).setEnable();
                        } else{
                            ((ThuisActivity)context).setEnable();
                        }
                        student.setStopStuddyingOrSleeping(0);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    /**
     * Method used to do nothing when nothing is selected.
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
