package com.example.dennis.studlife;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/**
 * Created by dennis on 6-6-2016.
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
