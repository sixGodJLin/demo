package com.example.linj.myapplication.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.linj.myapplication.view.calendar.Calendar;
import com.example.linj.myapplication.view.calendar.CalendarUtil;
import com.example.linj.myapplication.view.calendar.MonthView;

/**
 * 月视图实现类
 *
 * @author JLin
 */
public class SimpleMonthView extends MonthView {
    /**
     * 今天的背景圆形半径
     */
    private int mCurRadius;

    /**
     * 上角标圆形半径
     */
    private float mSchemeRadius;

    private int mPaddingEnd;

    public SimpleMonthView(Context context) {
        super(context);
        mSchemeRadius = CalendarUtil.dipToPx(getContext(), 8);
        mPaddingEnd = CalendarUtil.dipToPx(getContext(), 14);
    }

    @Override
    protected void onPreviewHook() {
        mCurRadius = Math.min(mItemWidth, mItemHeight) / 2;
    }

    @Override
    protected void onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mCurRadius, calendar.isCurrentDay() ? mCurCircleDayPaint : mSelectedPaint);
    }


    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int offset = mItemHeight / 7;
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;

        if (calendar.isCurrentDay()) {
            canvas.drawCircle(cx, cy, mCurRadius, mCurCircleDayPaint);
        }

        if (hasScheme) {
            canvas.drawCircle(x + mItemWidth - mPaddingEnd, y + mSchemeRadius, mSchemeRadius, mSchemeCirclePaint);
                Paint.FontMetrics fontMetrics = mSchemeTextPaint.getFontMetrics();
            canvas.drawText(calendar.getScheme(), x + mItemWidth - mPaddingEnd, y + mSchemeRadius + (fontMetrics.bottom - fontMetrics.top) / 4, mSchemeTextPaint);
        }

        canvas.drawText(String.valueOf(calendar.getDay()), cx, isShowLunar ? baselineY - offset : baselineY, calendar.isCurrentDay() ? mCurDayTextPaint :
                isSelected ? mCurDayTextPaint : mTextPaint);
        if (isShowLunar) {
            canvas.drawText(calendar.getLunar(), cx, baselineY + offset, calendar.isCurrentDay() ? mCurLunarTextPaint :
                    isSelected ? mCurLunarTextPaint : mLunarTextPaint);
        }
    }
}
