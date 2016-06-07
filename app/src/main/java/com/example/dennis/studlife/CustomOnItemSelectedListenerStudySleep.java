package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


/**
 * Created by dennis on 5-6-2016.
 */
public class CustomOnItemSelectedListenerStudySleep implements AdapterView.OnItemSelectedListener{
    private Student student;
    private String whatToDo;
    private Spinner spinner;
    private Spinner parent;
    private Context context;

    public CustomOnItemSelectedListenerStudySleep(Student student, String whatToDo, Spinner spinner, Context context){
        this.student = student;
        this.whatToDo = whatToDo;
        this.spinner = spinner;
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.parent = (Spinner) parent;
        int minutes = position * 20;
        long ms = minutes*60*1000;
        if (minutes > 0) {
            student.setStopStuddying(student.getStopStuddying() + ms);
            if (whatToDo == "study") {
                ((CollegeActivity)context).setEnable(); // word niet uitgevoerd? Wel buiten de if's
                student.setStudieVoortgang(student.getStudieVoortgang() + position);
                //parent.setEnabled(false);
                //spinner.setEnabled(false);
                //parent.setClickable(false);
                //spinner.setClickable(false);
                doNothing();
            }
            if (whatToDo == "sleep") {
                parent.setClickable(false);
                spinner.setClickable(false);
                doNothing();
            }
        }
        if(student.getStopStuddying() > 0){
            doNothing();
        }
    }

    public void doNothing(){
        Activity act = new Activity();
        act.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                while(student.getStopStuddying() != 0) {
                    if (student.getStopStuddying() <= Time.time) {

                        parent.setEnabled(true);
                        spinner.setEnabled(true);
                        spinner.setClickable(true);
                        parent.setClickable(true);
                        student.setStopStuddying(0);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
