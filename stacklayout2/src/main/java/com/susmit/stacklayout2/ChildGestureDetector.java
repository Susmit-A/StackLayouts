package com.susmit.stacklayout2;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ChildGestureDetector extends GestureDetector {

    private View child;

    public ChildGestureDetector(Context context, OnGestureListener listener){
        super(context, listener);
    }

    public View getView() {
        return child;
    }

    public void setView(View v){
        child = v;
    }

    public boolean onTouchEvent(View v, MotionEvent ev) {
        child = v;
        return onTouchEvent(ev);
    }
}
