package com.example.linj.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 * @date 2018/11/2
 */
public class MyView extends View {
    Paint paint;
    Path path;
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    double[] origin;

    float mStartY;
    float width;
    float xishu;
    float drawX = 0;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        CornerPathEffect cornerPathEffect = new CornerPathEffect(200);
        paint.setPathEffect(cornerPathEffect);

        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0, mStartY);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mStartY = height / 2;
        xishu = mStartY / 100;
    }

    public void setDy(double dy) {
        dy *= xishu;
        if (drawX == 0) {
            path = new Path();
            path.moveTo(drawX, (float) dy);
        } else {
            path.lineTo(drawX, (float) dy);
        }
        drawX += 10;
        if (drawX >= getMeasuredWidth()) {
            drawX = 0;
        }
        postInvalidate();
    }

    private double dy1;
    int index = 0;
    boolean isStart;

    public void setData(double[] data) {
        origin = data;
    }

    public void start() {
        if (isStart) {
            return;
        }
        if (scheduledThreadPoolExecutor == null || scheduledThreadPoolExecutor.isShutdown()) {
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        }
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            dy1 = origin[index];
            setDy(dy1);
            index++;
            if (index == 100) {
                index = 0;
            }
        }, 100, 1000, TimeUnit.MILLISECONDS);
        isStart = true;
    }

    public void setPaintColor(String color) {
        paint.setColor(Color.parseColor(color));
    }


    public void onDestroy() {
        if (scheduledThreadPoolExecutor != null) {
            scheduledThreadPoolExecutor.shutdown();
        }
    }
}
