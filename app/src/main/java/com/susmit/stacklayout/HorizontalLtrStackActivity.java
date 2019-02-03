package com.susmit.stacklayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.susmit.stacklayout2.HorizontalStackLayout;
import com.susmit.stacklayout2.VerticalStackLayout;

class HorizontalLtrStackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ltr_stack);

        final float scale = getResources().getDisplayMetrics().density;

        ImageView view = new ImageView(this);
        view.setLayoutParams(new LinearLayout.LayoutParams((int)(200 * scale + 0.5f), (int)(300 * scale + 0.5f)));
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((HorizontalStackLayout)findViewById(R.id.hltr)).addView(view);
    }
}
