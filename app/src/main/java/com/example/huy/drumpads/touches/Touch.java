package com.example.huy.drumpads.touches;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.*;

/**
 * Created by Huy on 6/16/2017.
 */

public class Touch {
    private float x;
    private float y;
    private int touchId;
    private TouchAction action;

    public Touch(float x, float y, int touchId, TouchAction action) {
        this.x = x;
        this.y = y;
        this.touchId = touchId;
        this.action = action;
    }

    public int getTouchId() {
        return touchId;
    }

    public TouchAction getAction() {
        return action;
    }

    public boolean checkHit(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bot = top + v.getHeight();
        return x > left && x < right && y > top && y < bot;
    }

    public static List<Touch> processEvent(MotionEvent event){
        ArrayList<Touch> touches = new ArrayList<>();

        int maskAction = event.getActionMasked();

        if(maskAction == ACTION_DOWN || maskAction == ACTION_POINTER_DOWN){
            Touch touch = getTouch(event, event.getActionIndex(), TouchAction.DOWN);
            touches.add(touch);
        } else if(maskAction == ACTION_UP || maskAction == ACTION_POINTER_UP){
            Touch touch = getTouch(event, event.getActionIndex(), TouchAction.UP);
            touches.add(touch);
        } else if(maskAction == ACTION_MOVE){
            for(int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex ++){
                Touch touch = getTouch(event,pointerIndex,TouchAction.MOVE);
                touches.add(touch);
            }
        }

        return touches;
    }

    private static Touch getTouch(MotionEvent event, int pointerIndex, TouchAction action) {
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        int id = event.getPointerId(pointerIndex);
        return new Touch(x, y, id, action);
    }

    @Override
    public boolean equals(Object obj) {
        Touch other = (Touch)obj;
        return other.touchId == this.touchId;
    }
}
