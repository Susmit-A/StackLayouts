package com.susmit.stacklayout2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;

public class VerticalStackLayout extends LinearLayout implements GestureDetector.OnGestureListener{

    GestureDetector gestureDetector;

    int peekViewCount;
    int peekSize;
    int upperExtremeRotation;
    int lowerExtremeRotation;

    float previousViewVisibility;
    boolean persistRotation;

    public void setPeekSize(int peekSize) {
        this.peekSize = peekSize;
    }

    public int getPeekSize() {
        return peekSize;
    }

    public void setPeekViewCount(int peekViewCount) {
        this.peekViewCount = peekViewCount;
    }

    public int getPeekViewCount() {
        return peekViewCount;
    }

    public void setUpperExtremeRotation(int upperExtremeRotation) {
        this.upperExtremeRotation = upperExtremeRotation;
    }

    public int getUpperExtremeRotation() {
        return upperExtremeRotation;
    }

    public void setLowerExtremeRotation(int lowerExtremeRotation) {
        this.lowerExtremeRotation = lowerExtremeRotation;
    }

    public int getLowerExtremeRotation() {
        return lowerExtremeRotation;
    }

    public void setPreviousViewVisibility(float previousViewVisibility) {
        this.previousViewVisibility = previousViewVisibility > 1.0f ? 1.0f : previousViewVisibility;
    }

    public float getPreviousViewVisibility() {
        return previousViewVisibility;
    }

    public void setPersistRotation(boolean persistRotation) {
        this.persistRotation = persistRotation;
    }

    public boolean isPersistRotation() {
        return persistRotation;
    }

    public VerticalStackLayout(Context context) {
        super(context);
        setOrientation(VERTICAL);
        gestureDetector = new GestureDetector(context, this);
    }

    public VerticalStackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.VerticalStackLayout,
                0, 0);

        try {
            upperExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.VerticalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.VerticalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.VerticalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.VerticalStackLayout_persistRotation, false);
        }catch (Exception ignored){

        }

        gestureDetector = new GestureDetector(context, this);
    }

    public VerticalStackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.VerticalStackLayout,
                defStyleAttr, 0);

        try {
            upperExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.VerticalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.VerticalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.VerticalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.VerticalStackLayout_persistRotation, false);
        }catch (Exception ignored){

        }
        gestureDetector = new GestureDetector(context, this);
    }

    public VerticalStackLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(VERTICAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.VerticalStackLayout,
                defStyleAttr, defStyleRes);

        try {
            upperExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.VerticalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.VerticalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.VerticalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.VerticalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.VerticalStackLayout_persistRotation, false);
        }catch (Exception ignored){

        }
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count = getChildCount();
        int startCount = 0;

        //Get the number of non-hidden views
        if(count > peekViewCount)
            startCount = count - peekViewCount;

        //Draw hidden views
        for(int i=0;i<startCount;i++){
            if(i==0) continue;
            View child = getChildAt(i);
            View firstChild = getChildAt(0);
            child.layout(firstChild.getLeft(), firstChild.getTop(), firstChild.getRight(), firstChild.getTop()+child.getHeight());
        }

        //Draw non-hidden views
        for(int i=startCount ; i<count ; i++){
            if(i==0) continue;
            View child = getChildAt(i);
            View prevChild = getChildAt(i-1);
            child.layout(child.getLeft(), prevChild.getTop()+peekSize, child.getRight(), prevChild.getTop()+peekSize+child.getHeight());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Keep rotation after user is no longer interacting with the screen?
        if(!isPersistRotation() && event.getAction()==MotionEvent.ACTION_UP){
            int childCount = getChildCount();
            for(int i=0;i<childCount;i++)
                getChildAt(i).setRotationX(0);

        }
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        performClick();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        int childCount = getChildCount();

        for (int i = 1; i < childCount; i++) {
            View child = getChildAt(i);
            View prevChild = getChildAt(i - 1);

            //Set rotations for extremes
            if (child.getY() >= prevChild.getY() + prevChild.getHeight() * previousViewVisibility) {
                child.setRotationX(-lowerExtremeRotation);
                if (i == 1)
                    prevChild.setRotationX(-lowerExtremeRotation);
            } else if (child.getY() == prevChild.getY() + peekSize || child.getY() == prevChild.getY()) {
                child.setRotationX(upperExtremeRotation);
                if (i == 1)
                    prevChild.setRotationX(upperExtremeRotation);
            } else {
                child.setRotationX(0);
                if (i == 1)
                    prevChild.setRotationX(0);
            }

            //Set new Y value for child. This may be updated later before function ends
            child.setY(child.getY() - distanceY * i / 2);

            //Set minimum Y limit for child, WRT previous child

            //Whether child will be hidden by later children
            if (i < getChildCount() - peekViewCount && child.getY() < prevChild.getY()) {
                child.setY(prevChild.getY());
            } else if (i >= getChildCount() - peekViewCount && child.getY() < prevChild.getY() + peekSize) {
                child.setY(prevChild.getY() + peekSize);
            } else if (child.getY() > prevChild.getY() + prevChild.getHeight() * previousViewVisibility)
                child.setY(prevChild.getY() + prevChild.getHeight() * previousViewVisibility);

        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        performLongClick();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityX > 1 || velocityX < -1){
            int childCount = getChildCount();
            for(int i=0;i<childCount;i++) {
                if(getChildAt(i)==getFocusedChild())
                    Log.e("Swiped child index", String.valueOf(i));
            }
        }
        return false;
    }

    @Override
    public void addView(View child) {
        super.addView(child);

        if(getChildCount() < peekViewCount || getChildCount()==1) return;
        View prevChild = getChildAt(getChildCount()-2);
        if(getChildCount()==2) {
            child.setX(prevChild.getX());
            child.setY(prevChild.getY() + peekSize);
        }
        else{
            View secondPrevChild = getChildAt(getChildCount() - 3);
            child.setX(prevChild.getX());
            child.setY(prevChild.getY() + (prevChild.getY() - secondPrevChild.getY()));
        }
    }
}
