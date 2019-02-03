package com.susmit.stacklayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.susmit.stacklayout2.VerticalStackLayout;

class VerticalStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_stack_activity);

        final float scale = getResources().getDisplayMetrics().density;

        ImageView view = new ImageView(this);
        view.setLayoutParams(new LinearLayout.LayoutParams((int)(200 * scale + 0.5f), (int)(300 * scale + 0.5f)));
        view.setImageDrawable(new ColorDrawable(Color.YELLOW));
        ((VerticalStackLayout)findViewById(R.id.vsl)).addView(view);
    }

}
