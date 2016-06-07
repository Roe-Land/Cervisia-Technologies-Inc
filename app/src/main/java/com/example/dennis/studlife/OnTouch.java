package com.example.dennis.studlife;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dennis on 6-6-2016.
 */
public class OnTouch implements View.OnTouchListener {

    public boolean onTouch (View v, MotionEvent event)
    {
        DragShadow dragShadow = new DragShadow(v);
        ClipData data = ClipData.newPlainText("","");
        v.startDrag(data,dragShadow,v,0);
        return false;
    }
}
