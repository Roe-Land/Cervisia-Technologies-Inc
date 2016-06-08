package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Uitgaan activity. This is where our stud can go to the pub, and do stupid things.
 */
public class UitgaanActivity extends AppCompatActivity implements Activitys {
    private Student student;
    private ProgressBar four, five, six;
    private Spinner spinner;
    private ImageView stud;
    private TextView social, study, money;
    private Handler animationSwitchHandler, uiHandler;

    /**
     * OnCreate method.
     * - Sets the layout.
     * - Loads in our stud from the application class.
     * - Checks if our stud is still alive.
     * - Sets the handlers, views and progressbars.
     * - Loads in the spinner used for switching locations.
     * - Animates our stud.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitgaan);
        student = ApplicationClass.student;
        student.checkDead(this);
        setHandlers();
        findViews();
        setProgressBarsAndTextViews();
        student.setClass(this);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this));
        student.updateProgressbars();
        stud.post(new Runnable(){
            @Override
            public void run(){
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });
    }

    /**
     * Creates and sets the handlers so that other threads could do things on the mainthread.
     */
    public void setHandlers(){
        animationSwitchHandler = new Handler();
        uiHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                money.setText(msg.what + " Euro");
            }
        };
    }

    /**
     * Finds the views for all the objects.
     */
    public void findViews(){
        spinner = (Spinner) findViewById(R.id.spinner3);
        stud = (ImageView) findViewById(R.id.studanimation);
        findViewById(R.id.beer).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.pizza).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.toeter).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.hamburger).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.fries).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.mouth).setOnDragListener(new DropListener(stud, this, animationSwitchHandler));
    }

    /**
     * Updates the social status and study progess for our stud.
     */
    public void updateSocialStudy(){
        social.setText("Social: " + student.getSocialGod());
        study.setText("Study: " + student.getStudyProgress());
    }

    /**
     * Method to set all the progressbars to their right value. Initialises them first.
     * Also updates the textviews which hold the amount of euro's, study status and social status.
     */
    public void setProgressBarsAndTextViews(){
        four = (ProgressBar) findViewById(R.id.progressBar4);
        five = (ProgressBar) findViewById(R.id.progressBar5);
        six = (ProgressBar) findViewById(R.id.progressBar6);
        four.setProgress(student.getEnergy());
        five.setProgress(student.getHappiness());
        six.setProgress(student.getHealth());
        money = (TextView) findViewById(R.id.money1);
        money.setText(student.getMoney() + " Euro");
        social = (TextView) findViewById(R.id.socialPoints);
        social.setText("Social: " + student.getSocialGod());
        study = (TextView) findViewById(R.id.studyPoints);
        study.setText("Study: " + student.getStudyProgress());
    }

    /**
     * Method to again set the progressbars and textviewvalues.
     * This time they are not initialised, and are thus used for updating when
     * our stud is already in the city/pub.
     */
    @Override
    public void setProgressBarsAndTextViewsValues() {
        four.setProgress(student.getEnergy());
        five.setProgress(student.getHappiness());
        six.setProgress(student.getHealth());
        uiHandler.sendMessage(uiHandler.obtainMessage((int) student.getMoney()));
    }

    /**
     * Saves the current state when the activity is aborted.
     */
    @Override
    protected void onStop(){
        student.save(this);
        super.onStop();
    }

    /**
     * Saves the current state when the back button on the device is pressed.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        student.save(this);
        finishAffinity();
    }

    /**
     * Method implemented from Activities. Animates the stud to chew when consumables are fed to him.
     * @param event The DragEvent.
     */
    public void dropDrinkOrFood(DragEvent event){
        stud.setBackgroundResource(R.drawable.studanimation_kauwen);
        ((AnimationDrawable) stud.getBackground()).start();

    }

    /**
     * Incorporates scripted random events. These can only be unlocked after our
     * stud has had enough beers to do something like this.
     * @param v
     */
    public void event(View v){
        if(student.getBierCounter() >= 5) {
            Intent intent = new Intent(this, Events.class);
            startActivity(intent);
            student.setBierCounter(0);
        }
        else{
            Toast.makeText(this, "Drink eerst eens even door!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method implemented from Activities. Differentiates between the different consumables,
     * so that the progressbars can be updated accordingly.
     * @param v The consumable.
     * @param event The DragEvent.
     */
    public void dropSwitch(ImageView v, DragEvent event){
        switch (v.getId()){
            case R.id.beer :
                student.eatOrDrink(this, 5, -6, 1, (float) 1.5, true, event);
                break;
            case R.id.pizza :
                student.eatOrDrink(this, 6, -4, -1, 5, false, event);
                break;
            case R.id.toeter :
                student.eatOrDrink(this, 8, -5, -1, 5, false, event);
                break;
            case R.id.hamburger :
                student.eatOrDrink(this, 5, -2, 0, 1, false, event);
                break;
            default :
                student.eatOrDrink(this, 4, -2, 0, (float) 1.5, false, event);
                break;
        }
    }
}
