package com.example.dennis.studlife;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.Random;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Events class. In which the scripted random events can be called.
 * Only applicable after drinking at least 5 beers.
 */
public class Events extends AppCompatActivity {
    private RelativeLayout r;
    private Student student;
    private Random random;

    /**
     * Gets the student from the ApplicationClass.
     * Runs a random event for out stud.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        student = ApplicationClass.student;
        r = (RelativeLayout)findViewById(R.id.layout);
        eventSwitch(randomInt());
    }

    /**
     * Finishes the scripted random event.
     * @param v
     */
    public void back(View v){
        finish();
    }

    /**
     * Calculates a random integer between 1 and 10.
     * @return
     */
    public int randomInt(){
        random = new Random();
        int r = random.nextInt(10) + 1;
        return r;
    }

    /**
     * Event switcher. Runs the actual scripted random events, in follow up of the choses random integer.
     * @param random
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void eventSwitch(int random){
        switch(random){
            case 1:
                r.setBackground(getResources().getDrawable(R.drawable.draken_jagen));
                student.doEvent(0, -4, 5, 0);
                Toast.makeText(this, "Gelukkig is het nog goed voor je sociale status, +5!", Toast.LENGTH_LONG).show();
                break;

            case 2:
                r.setBackground(getResources().getDrawable(R.drawable.oom_agent));
                student.doEvent(0, 5, 0, student.getMoneyFromStufi() - student.getMoneySpend());
                Toast.makeText(this, "Daar gaat je geld, je bent blut!", Toast.LENGTH_LONG).show();
                break;

            case 3:
                r.setBackground(getResources().getDrawable(R.drawable.muur_koppen));
                student.doEvent(20, 0, -3, 0);
                Toast.makeText(this, "Au! Doei gezondheid! Doei status!", Toast.LENGTH_LONG).show();
                break;

            case 4:
                r.setBackground(getResources().getDrawable(R.drawable.je_vindt_geld));
                Random geld = new Random();
                student.doEvent(0, -5, 0, -1 * ((geld.nextInt(5) + 1) * 10));
                Toast.makeText(this, "Lucky bastard!", Toast.LENGTH_LONG).show();
                break;

            case 5:
                r.setBackground(getResources().getDrawable(R.drawable.tactical_barfje));
                student.doEvent(-5, 0, 4, 0);
                Toast.makeText(this, "Zelfs de mensen om je heen vinden dit mooi! +4", Toast.LENGTH_LONG).show();
                break;

            case 6:
                r.setBackground(getResources().getDrawable(R.drawable.rondje_geven));
                student.doEvent(0, 0, 8, 5);
                Toast.makeText(this, "Geen geld, maar populairder dan ooit!", Toast.LENGTH_LONG).show();
                break;

            case 7:
                r.setBackground(getResources().getDrawable(R.drawable.dronken_aankoop));
                student.doEvent(0, 5, 2, (student.getMoney())/2);
                Toast.makeText(this, "Ach, je krijgt al bijna weer stufi!", Toast.LENGTH_LONG).show();
                break;

            case 8:
                r.setBackground(getResources().getDrawable(R.drawable.vechten));
                student.doEvent(15,0,4,0);
                Toast.makeText(this, "Gaaf, maar nu doet wel alles pijn! -15", Toast.LENGTH_LONG).show();

            case 9:
                r.setBackground(getResources().getDrawable(R.drawable.app_ex));
                student.doEvent(0,8,-5,0);
                Toast.makeText(this, "Daar wordt je niet vrolijk van, zij ook niet..", Toast.LENGTH_LONG).show();

            default:
                r.setBackground(getResources().getDrawable(R.drawable.souvenir_scoren));
                student.doEvent(0, -6, 5, 0);
                Toast.makeText(this, "Plezier met vrienden, +5!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Saves the current state after the app closes down.
     */
    @Override
    protected void onStop(){
        student.save(this);
        super.onStop();
    }
}
