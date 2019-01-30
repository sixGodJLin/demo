package com.example.linj.myapplication.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

/**
 * 月视图基类
 *
 * @author JLin
 */
public abstract class BaseMonthView extends BaseView {

    MonthViewPager mMonthViewPager;
    /**
     * 当前日历卡年份
     */
    protected int mYear;

    /**
     * 当前日历卡月份
     */
    protected int mMonth;

    /**
     * 日历的行数
     */
    protected int mLineCount;

    /**
     * 日历高度
     */
    protected int mHeight;

    /**
     * 偏移的数量
     */
    protected int offDiff;

    public BaseMonthView(Context context) {
        super(context);
    }

    /**
     * 初始化日期
     *
     * @param year  year
     * @param month month
     */
    final void initMonthWithDate(int year, int month) {
        mYear = year;
        mMonth = month;
        initCalendar();

        mHeight = CalendarUtil.getMonthViewHeight(year, month, mItemHeight, mDelegate.getWeekStart());
    }

    /**
     * 初始化日历
     */
    @SuppressLint("WrongConstant")
    private void initCalendar() {

        offDiff = CalendarUtil.getMonthEndDiff(mYear, mMonth, mDelegate.getWeekStart());
        int preDiff = CalendarUtil.getMonthViewStartDiff(mYear, mMonth, mDelegate.getWeekStart());
        int monthDayCount = CalendarUtil.getMonthDaysCount(mYear, mMonth);

        mItems = CalendarUtil.initCalendarForMonthView(mYear, mMonth, mDelegate.getCurrentDay(), mDelegate.getWeekStart());

        if (mItems.contains(mDelegate.getCurrentDay())) {
            mCurrentItem = mItems.indexOf(mDelegate.getCurrentDay());
        } else {
            mCurrentItem = mItems.indexOf(mDelegate.mSelectedCalendar);
        }

        if (mCurrentItem > 0) {
            mCurrentItem = -1;
        }
        mLineCount = (preDiff + monthDayCount + offDiff) / 7;

        addSchemesFromMap();
        invalidate();
    }

    /**
     * 获取点击选中的日期
     *
     * @return return
     */
    protected Calendar getIndex() {
        int indexX = (int) mX / mItemWidth;
        if (indexX >= 7) {
            indexX = 6;
        }
        int indexY = (int) mY / mItemHeight;
        int position = indexY * 7 + indexX;// 选择项
        if (position >= 0 && position < mItems.size()) {
            return mItems.get(position);
        }
        return null;
    }

    /**
     * 记录已经选择的日期
     *
     * @param calendar calendar
     */
    final void setSelectedCalendar(Calendar calendar) {
        mCurrentItem = mItems.indexOf(calendar);
    }

    @Override
    void updateItemHeight() {
        super.updateItemHeight();
        mHeight = CalendarUtil.getMonthViewHeight(mYear, mMonth, mItemHeight, mDelegate.getWeekStart());
    }

    /**
     * 获取选中的下标
     *
     * @param calendar calendar
     * @return 获取选中的下标
     */
    protected final int getSelectedIndex(Calendar calendar) {
        return mItems.indexOf(calendar);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mLineCount != 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(mHeight, View.MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
