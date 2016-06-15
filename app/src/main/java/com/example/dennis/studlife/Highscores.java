package com.example.dennis.studlife;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Highscores class. Used to do the computations for the highscores.
 * Such that they are sorted, and one can add a highscore into the middle of the list.
 */
public class Highscores {
    private SharedPreferences HighscoresPrefs2;
    private String names[];
    private int highscores[];

    /**
     * Creating a new instance of this class, it will automatically load the previous data in from the
     * shared preferences. Such that highscores are kept even after closing the app.
     *
     * Creates two arrays from the sharedpreferences to do computations on.
     */
    public Highscores(Context context) {
        HighscoresPrefs2 = context.getSharedPreferences("Highscore2", 0);
        names = new String[10];
        highscores = new int[10];

        for (int x = 0; x < 10; x++) {
            names[x] = HighscoresPrefs2.getString("name" + x, " -");
            highscores[x] = HighscoresPrefs2.getInt("highscore" + x, 0);
        }
    }

    /**
     * Combines the name and score array so that they can be put in the listview in the HighscoresActivity easily.
     * @return
     */
    public ArrayList combineArrays(){
        ArrayList<String> combined = new ArrayList<String>();
        for (int x = 0; x < 10; x++){
            combined.add(x, names[x] + "     " + highscores[x]);
        }
        return combined;
    }

    /**
     * Adds a highscore to the list. If a highscore falls into the middle of the list,
     * it will move down the lower highscores to make place.
     * Also puts these directly into the sharedpreferences for saving.
     *
     * @param name Name of the user
     * @param highscore Score of the user
     */
    public boolean addHighscore(String name, int highscore) {
        int rank;
        for (rank = 0; rank < 10 && highscores[rank] > highscore; rank++){
            if (rank == 9) return false;
        }
        for (int y = 0; y < (9 - rank); y++) {
            names[9-y] = names[8-y];
            highscores[9-y] = highscores[8-y];
        }
        names[rank] = name;
        highscores[rank] = highscore;
        SharedPreferences.Editor editor = HighscoresPrefs2.edit();
        for (int q = 0; q < 10; q++) {
            editor.putString("name"+q, names[q]);
            editor.putInt("highscore"+q, highscores[q]);
        }
        editor.commit();
        return true;
    }
}
