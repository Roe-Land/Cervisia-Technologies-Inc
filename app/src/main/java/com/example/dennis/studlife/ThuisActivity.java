package com.example.dennis.studlife;

import android.annotation.TargetApi;
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
import android.widget.TextView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Thuis activity. This is the home of our stud, where he can get some sleep and a healthy meal.
 */
public class ThuisActivity extends AppCompatActivity implements Activitys{
    private Student student;
    private ProgressBar one, two, three;
    private Spinner spinner, sleepingSpinner;
    private ImageView stud;
    private TextView social, study, money;
    private Handler spinnerHandler, animationSwitchHandler, uiHandler;

    /**
     * OnCreate method.
     * - Sets the layout.
     * - Loads in our stud from the application class.
     * - Checks if our stud is still alive.
     * - Sets the handlers, views and progressbars.
     * - Loads in the spinners used for switching locations and sleeping.
     * - Animates our stud.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuis);
        student = ApplicationClass.student;
        student.checkDead(this);
        setHandlers();
        findViews();
        setProgressBarsAndTextViews();
        student.setClass(this);
        spinner.setOnItemSelectedListener( new CustomOnItemSelectedListener(this));
        sleepingSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListenerStudySleep(student, "sleep", spinner, this, stud));
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
        spinnerHandler = new Handler();
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
        spinner = (Spinner) findViewById(R.id.spinner);
        sleepingSpinner = (Spinner) findViewById(R.id.sleeping);
        stud = (ImageView) findViewById(R.id.studanimation);
        findViewById(R.id.pear).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.milk).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.apple).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.bread).setOnTouchListener(new OnTouch(student));
        findViewById(R.id.carrot).setOnTouchListener(new OnTouch(student));
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
     * Enables the spinners, and sets our stud to non-sleep mode.
     */
    public void setEnable(){
        spinnerHandler.post(new Runnable(){
            @Override
            public void run() {
                spinner.setEnabled(true);
                sleepingSpinner.setEnabled(true);
                student.setIsSleeping(false);
                stud.setBackgroundResource(R.drawable.studanimation);
                ((AnimationDrawable) stud.getBackground()).start();
            }
        });
    }

    /**
     * Method to set all the progressbars to their right value. Initialises them first.
     * Also updates the textviews which hold the amount of euro's, study status and social status.
     */
    public void setProgressBarsAndTextViews(){
        one = (ProgressBar) findViewById(R.id.progressBar);
        two = (ProgressBar) findViewById(R.id.progressBar2);
        three = (ProgressBar) findViewById(R.id.progressBar3);
        one.setProgress(student.getEnergy());
        two.setProgress(student.getHappiness());
        three.setProgress(student.getHealth());
        money = (TextView) findViewById(R.id.money);
        money.setText(student.getMoney() + " Euro");
        social = (TextView) findViewById(R.id.socialPoints);
        social.setText("Social: " + student.getSocialGod());
        study = (TextView) findViewById(R.id.studyPoints);
        study.setText("Study: " + student.getStudyProgress());
    }

    /**
     * Method to again set the progressbars and textviewvalues.
     * This time they are not initialised, and are thus used for updating when
     * our stud is already at home.
     */
    @Override
    public void setProgressBarsAndTextViewsValues(){
        one.setProgress(student.getEnergy());
        two.setProgress(student.getHappiness());
        three.setProgress(student.getHealth());
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
     * Saves the current state when the activity is destroyed.
     */
    @Override
    public void onDestroy(){
        student.save(this);
        super.onDestroy();
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
     * Method implemented from Activities. Differentiates between the different consumables,
     * so that the progressbars can be updated accordingly.
     * @param v The consumable.
     * @param event The DragEvent.
     */
    public void dropSwitch(ImageView v, DragEvent event){
        switch (v.getId()){
            case R.id.milk :
                student.eatOrDrink(this, -4, 2, -2, (float) 0.5, false, event);
                break;
            case R.id.apple :
                student.eatOrDrink(this, -3, 0 , -3, (float) 0.25, false, event);
                break;
            case R.id.pear :
                student.eatOrDrink(this, -4, 1, -3, (float) 0.25, false, event);
                break;
            case R.id.carrot :
                student.eatOrDrink(this, -5, 5, -4, (float) 0.5, false, event);
                break;
            default :
                student.eatOrDrink(this, -2, 0, -3, (float) 0.2, false, event);
                break;
        }
    }
}
