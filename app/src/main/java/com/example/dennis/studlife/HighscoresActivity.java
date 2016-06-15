package com.example.dennis.studlife;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Highscores activity. Used to display the highscores achieved in the app.
 */
public class HighscoresActivity extends AppCompatActivity {

    /**
     * Enters the arrays via an arrayadapter into the scrollable listview.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Highscores highscores = new Highscores(this);
        ListView listView = (ListView) findViewById(R.id.listViewHighscores);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highscores.combineArrays())
        {
            @Override
            public View getView(int position, View viewToConvert, ViewGroup parent){
                View view = super.getView(position, viewToConvert, parent);
                TextView tekstView = (TextView) view.findViewById(android.R.id.text1);
                tekstView.setTextColor(Color.WHITE);
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
    }
}
