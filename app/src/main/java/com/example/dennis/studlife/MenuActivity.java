package com.example.dennis.studlife;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * The activity used for the home menu, from which you can choose to play or see the highscores.
 */
public class MenuActivity extends AppCompatActivity {
    private Student student;
    private RelativeLayout menu;
    private AnimationDrawable animation;
    private Intent intent;

    /**
     * Runs the animation on a seperate thread to improve performance.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = (RelativeLayout) findViewById(R.id.layout);
        menu.post(new Runnable(){
            @Override
            public void run(){
                animation = ((AnimationDrawable) menu.getBackground());
                animation.start();
            }
        });
    }

    /**
     * Creates a new intent used to go to the highscores activity.
     * @param v
     */
    public void viewHighscores(View v){
        intent = new Intent(this, HighscoresActivity.class);
        startActivity(intent);
    }

    /**
     * Gets our stud from the ApplicationClass.
     * Also creates the timethread.
     * Checks what our stud was doing when the app was aborted (nothing, sleep, study)
     * and goes to that location accordingly.
     *
     * @param v
     */
    public void toGame(View v) {
        animation.stop();
        ApplicationClass.student = student.get(this);
        student = ApplicationClass.student;
        student.makeTimeThread();
        if(student.getIsStudying()) {
            intent = new Intent(this, CollegeActivity.class);
        } else {
            intent = new Intent(this, ThuisActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
