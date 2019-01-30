package com.example.linj.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linj.myapplication.R;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author JLin
 * @date 2018/11/1
 */
public class SwipeView extends LinearLayout {
    private TextView textView;
    LinearLayout.LayoutParams params;
    private boolean isClose = false;

    public SwipeView(Context context) {
        super(context);
        params = new LayoutParams((int) getContext().getResources().getDimension(R.dimen.px500),
                (int) getContext().getResources().getDimension(R.dimen.px300));

        textView = new TextView(context);
        textView.setText("123");
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setBackgroundColor(Color.RED);
        textView.setLayoutParams(params);
        textView.setPadding((int) getContext().getResources().getDimension(R.dimen.px32), 0, 0, 0);

        addView(textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.scrollBy((int) getContext().getResources().getDimension(R.dimen.px5),
                        (int) getContext().getResources().getDimension(R.dimen.px5));
            }
        });
    }
}
