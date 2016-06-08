package com.example.dennis.studlife;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * DragShadow class. Used for the drag and drop functionality.
 * Makes a shadow of the object dragged, so the user can see it under his finger.
 */
public class DragShadow extends View.DragShadowBuilder {

    public DragShadow(View view)
    {
        super(view);
    }
    @Override
    public void onDrawShadow(Canvas canvas)
    {
        super.onDrawShadow(canvas);
    }
    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint)
    {
        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
    }
}
