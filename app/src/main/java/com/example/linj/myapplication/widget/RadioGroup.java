package com.example.linj.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.linj.myapplication.R;


/**
 * Created by JLin on 2018/5/25
 *
 * @author JLin
 */

public class RadioGroup extends RelativeLayout {
    private TextView tvTitle;
    private MyRadioButton rbt1;
    private MyRadioButton rbt2;
    private MyRadioButton rbt3;
    private MyRadioButton rbt4;

    private String title;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public RadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_radio_group, this, true);
        tvTitle = findViewById(R.id.tv_title);
        rbt1 = findViewById(R.id.rbt_1);
        rbt2 = findViewById(R.id.rbt_2);
        rbt3 = findViewById(R.id.rbt_3);
        rbt4 = findViewById(R.id.rbt_4);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RadioGroup);
        if (ta != null) {
            title = ta.getString(R.styleable.RadioGroup_radio_group_title);
            text1 = ta.getString(R.styleable.RadioGroup_button_text_1);
            text2 = ta.getString(R.styleable.RadioGroup_button_text_2);
            text3 = ta.getString(R.styleable.RadioGroup_button_text_3);
            text4 = ta.getString(R.styleable.RadioGroup_button_text_4);
            ta.recycle();
        }

        tvTitle.setText(title);

        if (!TextUtils.isEmpty(text1)) {
            rbt1.setVisibility(VISIBLE);
            rbt1.setText(text1);
        } else {
            rbt1.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(text2)) {
            rbt2.setVisibility(VISIBLE);
            rbt2.setText(text2);
        } else {
            rbt2.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(text3)) {
            rbt3.setVisibility(VISIBLE);
            rbt3.setText(text3);
        } else {
            rbt3.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(text4)) {
            rbt4.setVisibility(VISIBLE);
            rbt4.setText(text4);
        } else {
            rbt4.setVisibility(GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureHandler(widthMeasureSpec, (int) getResources().getDimension(R.dimen.px720));
        int height = measureHandler(heightMeasureSpec, (int) getResources().getDimension(R.dimen.px96));
        setMeasuredDimension(width, height);
    }

    private int measureHandler(int measureSpec, int defaultSize) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }
}
