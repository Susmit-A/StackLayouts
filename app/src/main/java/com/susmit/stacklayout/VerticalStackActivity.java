package com.susmit.stacklayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.susmit.stacklayout2.ChildGestureDetector;
import com.susmit.stacklayout2.VerticalStackLayout;

class VerticalStackActivity extends AppCompatActivity {

    ChildGestureDetector viewGestureDetector;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_stack_activity);

        final float scale = getResources().getDisplayMetrics().density;

        ImageView view = new ImageView(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int)(200 * scale + 0.5f), (int)(300 * scale + 0.5f));
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((VerticalStackLayout)findViewById(R.id.vsl)).addView(view, 3, p);

        viewGestureDetector = new ChildGestureDetector(VerticalStackActivity.this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Toast.makeText(VerticalStackActivity.this, " "+viewGestureDetector.getView().getY(), Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(velocityX > 200){
                    ((VerticalStackLayout)findViewById(R.id.vsl)).removeView(viewGestureDetector.getView());
                    return true;
                }
                return false;

            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewGestureDetector.onTouchEvent(v, event);
            }
        });

        view = new ImageView(this);
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((VerticalStackLayout)findViewById(R.id.vsl)).addView(view, p);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewGestureDetector.onTouchEvent(v, event);
            }
        });

    }

}
