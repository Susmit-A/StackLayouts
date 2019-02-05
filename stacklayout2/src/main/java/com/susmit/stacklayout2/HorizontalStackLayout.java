package com.susmit.stacklayout2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.List;

public class HorizontalStackLayout extends LinearLayout implements GestureDetector.OnGestureListener{

    GestureDetector gestureDetector;

    int peekViewCount;
    int peekSize;
    int upperExtremeRotation;
    int lowerExtremeRotation;

    float previousViewVisibility = 0.33f;
    boolean persistRotation = false;

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

    public HorizontalStackLayout(Context context) {
        super(context);
        //setOrientation(HORIZONTAL);
        gestureDetector = new GestureDetector(context, this);
    }

    public HorizontalStackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setOrientation(HORIZONTAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalStackLayout,
                0, 0);

        try {
            upperExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.HorizontalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.HorizontalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.HorizontalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.HorizontalStackLayout_persistRotation, false);
        }catch (Exception ignored){

        }
        gestureDetector = new GestureDetector(context, this);
    }

    public HorizontalStackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setOrientation(HORIZONTAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalStackLayout,
                0, 0);

        try {
            upperExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.HorizontalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.HorizontalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.HorizontalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.HorizontalStackLayout_persistRotation, false);
        }catch (Exception ignored){

        }
        gestureDetector = new GestureDetector(context, this);
    }

    public HorizontalStackLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //setOrientation(HORIZONTAL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalStackLayout,
                0, 0);

        try {
            upperExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_upperExtremeRotation, 2);
            lowerExtremeRotation = a.getInt(R.styleable.HorizontalStackLayout_lowerExtremeRotation, 2);
            peekSize = a.getInt(R.styleable.HorizontalStackLayout_peekSize, 20);
            peekViewCount = a.getInt(R.styleable.HorizontalStackLayout_peekViewCount, 3);
            previousViewVisibility = a.getFloat(R.styleable.HorizontalStackLayout_previousViewVisibility, 0.33f);
            persistRotation = a.getBoolean(R.styleable.HorizontalStackLayout_persistRotation, false);
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
            child.layout(firstChild.getLeft(), firstChild.getTop(), firstChild.getLeft() + child.getWidth(), firstChild.getBottom());
        }

        //Draw non-hidden views
        for(int i=startCount ; i<count ; i++){
            if(i==0) continue;
            View child = getChildAt(i);
            View prevChild = getChildAt(i-1);
            if(getLayoutDirection()==LAYOUT_DIRECTION_LTR)
                child.layout(prevChild.getLeft() + peekSize, child.getTop(), prevChild.getLeft() + peekSize + child.getWidth(), child.getBottom());
            else
                child.layout(prevChild.getLeft() - peekSize, child.getTop(), prevChild.getLeft() - peekSize + child.getWidth(), child.getBottom());
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
        if(getLayoutDirection()==LAYOUT_DIRECTION_LTR) {
            for (int i = 1; i < childCount; i++) {
                View child = getChildAt(i);
                View prevChild = getChildAt(i - 1);

                //Set rotations for extremes
                if (child.getX() >= prevChild.getX() + prevChild.getWidth() * previousViewVisibility) {
                    child.setRotationY(upperExtremeRotation);
                    if (i == 1)
                        prevChild.setRotationY(upperExtremeRotation);
                } else if (child.getX() == prevChild.getX() + peekSize || child.getX() == prevChild.getX()) {
                    child.setRotationY(-lowerExtremeRotation);
                    if (i == 1)
                        prevChild.setRotationY(-lowerExtremeRotation);
                } else {
                    child.setRotationY(0);
                    if (i == 1)
                        prevChild.setRotationY(0);
                }

                //Set new X value for child. This may be updated later before function ends
                child.setX(child.getX() - distanceX * i / 2);

                //Set minimum and maximum X limits for child, WRT previous child

                //Whether child will be hidden by later children
                if (i < getChildCount() - peekViewCount && child.getX() < prevChild.getX()) {
                    child.setX(prevChild.getX());
                } else if (i >= getChildCount() - peekViewCount && child.getX() < prevChild.getX() + peekSize) {
                    child.setX(prevChild.getX() + peekSize);
                } else if (child.getX() > prevChild.getX() + prevChild.getWidth() * previousViewVisibility)
                    child.setX(prevChild.getX() + prevChild.getWidth() * previousViewVisibility);
            }
        }
        else{
            for (int i = 1; i < childCount; i++) {
                View child = getChildAt(i);
                View prevChild = getChildAt(i - 1);

                //Set rotations for extremes
                if (child.getX() <= prevChild.getX() - prevChild.getWidth() * previousViewVisibility) {
                    child.setRotationY(-upperExtremeRotation);
                    if (i == 1)
                        prevChild.setRotationY(-upperExtremeRotation);
                } else if (child.getX() == prevChild.getX() - peekSize || child.getX() == prevChild.getX()) {
                    child.setRotationY(lowerExtremeRotation);
                    if (i == 1)
                        prevChild.setRotationY(lowerExtremeRotation);
                } else {
                    child.setRotationY(0);
                    if (i == 1)
                        prevChild.setRotationY(0);
                }

                //Set new X value for child. This may be updated later before function ends
                child.setX(child.getX() - distanceX * i / 2);

                //Set minimum and maximum X limits for child, WRT previous child

                //Whether child will be hidden by later children
                //Underscroll lock
                if (i < getChildCount() - peekViewCount && child.getX() > prevChild.getX() ) {
                    child.setX(prevChild.getX());
                } else if (i >= getChildCount() - peekViewCount && child.getX() > prevChild.getX() - peekSize ) {
                    child.setX(prevChild.getX() - peekSize);
                }

                //Overscroll lock
                else if (child.getX() < prevChild.getX() - prevChild.getWidth() * previousViewVisibility)
                    child.setX(prevChild.getX() - prevChild.getWidth() * previousViewVisibility);
            }
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        performLongClick();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityY > 1 || velocityY < -1){
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
            child.setY(prevChild.getY());
            if(getLayoutDirection()==LAYOUT_DIRECTION_LTR)
                child.setX(prevChild.getX() + peekSize);
            else
                child.setX(prevChild.getX() - peekSize);
        }
        else{
            View secondPrevChild = getChildAt(getChildCount() - 3);
            child.setY(prevChild.getY());
            if(getLayoutDirection()==LAYOUT_DIRECTION_LTR)
                child.setX(prevChild.getX() + (prevChild.getX() - secondPrevChild.getX()));
            else
                child.setX(prevChild.getX() - (prevChild.getX() - secondPrevChild.getX()));
        }
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        float nextX = 0.0f;
        float nextY = 0.0f;
        int numChildren = getChildCount();
        nextY = getChildAt(numChildren - 1).getY();

        if(getLayoutDirection()==LAYOUT_DIRECTION_LTR) {
            nextX = getChildAt(numChildren - 1).getX() - getChildAt(getChildCount() - 2).getX();
            nextX += getChildAt(getChildCount() - 1).getX();
        }else{
            nextX = - (getChildAt(numChildren - 1).getX() - getChildAt(getChildCount() - 2).getX());
            nextX -= getChildAt(getChildCount() - 1).getX();
        }
        for(int i=getChildCount()-1; i>index; i--){
            View childView = getChildAt(i);
            childView.setX(nextX);
            childView.setY(nextY);

            try {
                if(getLayoutDirection()==LAYOUT_DIRECTION_LTR) {
                    nextX = getChildAt(i - 1).getX() - getChildAt(i - 2).getX();
                    nextX += getChildAt(i - 1).getX();
                }else{
                    nextX = - (getChildAt(i - 1).getX() - getChildAt(i - 2).getX());
                    nextX -= getChildAt(i - 1).getX();
                }
            }catch (NullPointerException e){
                return;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = gestureDetector.onTouchEvent(ev) && ev.getAction()!=MotionEvent.ACTION_DOWN;
        //Keep rotation after user is no longer interacting with the screen?
        if(!isPersistRotation() && ev.getAction()==MotionEvent.ACTION_UP){
            int childCount = getChildCount();
            for(int i=0;i<childCount;i++)
                getChildAt(i).setRotationY(0);

        }
        return result;
    }

    @Override
    public void removeView(View view) {
        int index = indexOfChild(view);
        float curX = view.getX();
        float curY = view.getY();
        float tempX, tempY;
        for(int i=index+1; i<getChildCount();i++){
            View next = getChildAt(i);
            tempX = next.getX();
            tempY = next.getY();
            next.setX(curX);
            next.setY(curY);
            curX = tempX;
            curY = tempY;
        }
        super.removeView(view);
    }

    @Override
    public void removeViewAt(int index) {
        View view = getChildAt(index);
        float curX = view.getX();
        float curY = view.getY();
        float tempX, tempY;
        for(int i=index+1; i<getChildCount();i++){
            View next = getChildAt(i);
            tempX = next.getX();
            tempY = next.getY();
            next.setX(curX);
            next.setY(curY);
            curX = tempX;
            curY = tempY;
        }
        super.removeViewAt(index);
    }
}
