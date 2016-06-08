package com.example.dennis.studlife;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * DropListener class. Used to implement the drag and drop functionality.
 */
public class DropListener implements View.OnDragListener {
    private ImageView stud;
    private Activitys activity;
    private Handler animationSwitchHandler;

    /**
     * Sets everything to a local variable so they can be used.
     * @param stud
     * @param activity
     * @param animationSwitchHandler
     */
    public DropListener(ImageView stud, Activitys activity, Handler animationSwitchHandler){
        this.stud = stud;
        this.activity = activity;
        this.animationSwitchHandler = animationSwitchHandler;
    }

    /**
     * Stops the normal stud animation when the consumable is in the perimeter of stud's mouth.
     * If this is aborted, restarts the normal animation.
     * If the stud is actually fed, runs an chewing animation.
     * @param v
     * @param event
     * @return
     */
    public boolean onDrag(View v, DragEvent event)
    {
        int dragEvent = event.getAction();
        switch (dragEvent)
        {
            case DragEvent.ACTION_DRAG_ENTERED:
                ((AnimationDrawable) stud.getBackground()).stop();
                stud.setBackgroundResource(R.drawable.stud1_eten_1);
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                stud.setBackgroundResource(R.drawable.studanimation);
                ((AnimationDrawable) stud.getBackground()).start();
                break;

            case DragEvent.ACTION_DROP:
                ImageView dragged = (ImageView) event.getLocalState();
                activity.dropSwitch(dragged, event);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                animationSwitchHandler.postDelayed(new Runnable(){
                    public void run(){
                        stud.setBackgroundResource(R.drawable.studanimation);
                        ((AnimationDrawable) stud.getBackground()).start();
                    }
                },3000);

        }
        return true;
    }
}
