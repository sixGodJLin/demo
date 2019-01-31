package com.example.linj.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2018/12/20
 */
@SuppressLint("AppCompatCustomView")
public class MyRadioButton extends RadioButton {
    Drawable drawable;

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        drawable = typedArray.getDrawable(R.styleable.MyRadioButton_drawableLeft);
        drawable.setBounds(0, 0, (int) getResources().getDimension(R.dimen.px28), (int) getResources().getDimension(R.dimen.px28));
        setCompoundDrawables(drawable, null, null, null);
    }
}
