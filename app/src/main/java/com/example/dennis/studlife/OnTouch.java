package com.example.dennis.studlife;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * OnTouch class. Used for dragging and dropping consumables into our stud's mouth.
 */
public class OnTouch implements View.OnTouchListener {
    private Student student;

    /**
     * Constructor, enables our stud to be used by the other method.
     * @param student
     */
    public OnTouch(Student student){
        this.student = student;
    }

    /**
     * Checks whether our stud is sleeping (can't feed an asleep student).
     * If he's not, then drag and dropping is allowed.
     *
     * @param v
     * @param event
     * @return
     */
    public boolean onTouch (View v, MotionEvent event){
        if(!student.getIsSleeping()) {
            DragShadow dragShadow = new DragShadow(v);
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0);
            return false;
        }
        return false;
    }
}
