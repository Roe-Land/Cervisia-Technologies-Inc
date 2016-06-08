package com.example.dennis.studlife;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Game over activity. Is shown when our stud dies. Different game over screens are showed,
 * depending on the amount of social points and study progress.
 */
public class GameOverActivity extends AppCompatActivity {
    private Student student;
    private EditText name;
    private TextView score;

    /**
     * Creates the activity and includes the highscore, and a field to enter the user's name in.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        student = ApplicationClass.student;
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        setBackground(layout);
        name = (EditText) findViewById(R.id.enterName);
        score = (TextView) findViewById(R.id.score);
        score.setText("Score " + String.valueOf(student.calculateScores()));
        score.setTextSize(35);
        score.setTextColor(Color.WHITE);
    }

    /**
     * Sets the background of game over screen according to how much study progress was made
     * and how many social point have been gathered.
     * @param layout
     */
    public void setBackground(RelativeLayout layout){
        int studyPoints = student.getStudyProgress();
        int socialGod = student.getSocialGod();
        if ((studyPoints < (socialGod + 10)) && (studyPoints > (socialGod - 10))){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenbalans));
        }else if((socialGod < 20) && (studyPoints < 20)){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenniks));
        }else if(socialGod < studyPoints){
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdenstudie));
        }else{
            layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.overlijdensociaal));
        }
    }

    /**
     * Saves the highscore and name. Clears all other attributes and goes back to the home screen.
     * @param v
     */
    public void newGame(View v){
        String userName = String.valueOf(name.getText());
        Highscores highscores = new Highscores(this);
        highscores.addHighscore(userName, student.calculateScores());
        student.clear(this);
        ApplicationClass.student = student.get(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
