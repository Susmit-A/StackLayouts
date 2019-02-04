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
import com.susmit.stacklayout2.HorizontalStackLayout;

class HorizontalRtlStackActivity extends AppCompatActivity {

    ChildGestureDetector viewGestureDetector;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_rtl_stack);

        viewGestureDetector = new ChildGestureDetector(HorizontalRtlStackActivity.this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Toast.makeText(HorizontalRtlStackActivity.this, " "+viewGestureDetector.getView().getY(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });

        final float scale = getResources().getDisplayMetrics().density;

        ImageView view = new ImageView(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int)(200 * scale + 0.5f), (int)(300 * scale + 0.5f));
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((HorizontalStackLayout)findViewById(R.id.hrtl)).addView(view, 3, p);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewGestureDetector.onTouchEvent(v, event);
            }
        });

        view = new ImageView(this);
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((HorizontalStackLayout)findViewById(R.id.hrtl)).addView(view, p);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewGestureDetector.onTouchEvent(v, event);
            }
        });
    }
}
