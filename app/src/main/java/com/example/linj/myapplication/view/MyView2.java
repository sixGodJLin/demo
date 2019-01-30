package com.example.linj.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 * @date 2018/11/2
 */
public class MyView2 extends View {
    Paint paint1;
    Paint paint2;

    Path path1;
    Path path2;

    float xishu;

    float paintX1;
    float paintX2;

    private float barHeight = 20;

    int height;

    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setColor(Color.parseColor("#fabc6f"));
        paint1.setStrokeWidth(2);

        paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(Color.parseColor("#26cafc"));
        paint2.setStrokeWidth(2);

        path1 = new Path();
        path2 = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getMeasuredWidth();
        height = getMeasuredHeight();
        xishu = width / 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path1, paint1);
        canvas.drawPath(path2, paint2);
    }

    public void setDx(int dx1, int dx2) {
        paintX1 = dx1 * xishu;
        paintX2 = dx2 * xishu;

        path1 = new Path();
        path1.moveTo(0, 0);
        path1.lineTo(paintX1, 0);
        path1.quadTo(paintX1 + (barHeight * 3 / 2), barHeight / 2, paintX1, barHeight);
        path1.lineTo(0, barHeight);
        path1.close();

        path2 = new Path();
        path2.moveTo(0, height - barHeight);
        path2.lineTo(paintX2, height - barHeight);
        path2.quadTo(paintX2 + (barHeight * 3 / 2), height - (barHeight / 2), paintX2, height);
        path2.lineTo(0, height);
        path2.close();

        invalidate();
    }
}
