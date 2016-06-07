package com.example.dennis.studlife;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by dennis on 6-6-2016.
 */
public class DropListener implements View.OnDragListener {
    private ImageView stud;
    private Activitys activity;

    public DropListener(ImageView stud, Activitys activity){
        this.stud = stud;
        this.activity = activity;
    }
    public boolean onDrag(View v, DragEvent event)
    {
        int dragEvent = event.getAction();

        switch (dragEvent)
        {
            case DragEvent.ACTION_DRAG_ENTERED:
                ((AnimationDrawable) stud.getBackground()).stop();
                stud.setBackgroundResource(R.drawable.stud1_eten_1);
                Log.i("Drag Event", "Entered");
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                stud.setBackgroundResource(R.drawable.studanimation);
                ((AnimationDrawable) stud.getBackground()).start();
                Log.i("Drag Event", "Exited");
                break;

            case DragEvent.ACTION_DROP:
                Log.i("Drag Event", "Dropped");
                activity.dropDrinkOrFood(event);
        }
        return true;
    }


}
