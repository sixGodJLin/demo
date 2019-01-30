package com.example.linj.myapplication.view.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.example.linj.myapplication.R;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Google规范化的属性委托,
 * 代码量多，但是不影响阅读性
 */
final class CalendarViewDelegate {

    /**
     * 周起始：周日
     */
    static final int WEEK_START_WITH_SUN = 1;

    /**
     * 周起始：周一
     */
    static final int WEEK_START_WITH_MON = 2;

    /**
     * 周起始：周六
     */
    static final int WEEK_START_WITH_SAT = 7;

    /**
     * 周起始
     */
    private int mWeekStart;

    /**
     * 最小农历年份
     */
    static final int MIN_YEAR = 1900;
    /**
     * 最大农历年份
     */
    private static final int MAX_YEAR = 2099;

    /**
     * 是否显示农历时间、节日信息
     */
    private boolean isShowLunar;

    /**
     * 自定义的日历路径
     */
    private String mMonthViewClassPath;

    /**
     * 月视图类
     */
    private Class<?> mMonthViewClass;

    /**
     * 自定义周视图路径
     */
    private String mWeekViewClassPath;

    /**
     * 周视图类
     */
    private Class<?> mWeekViewClass;

    /**
     * 最小年份和最大年份
     */
    private int mMinYear, mMaxYear;

    /**
     * 最小年份和最大年份对应最小月份和最大月份
     */
    private int mMinYearMonth, mMaxYearMonth;

    /**
     * 最小年份和最大年份对应最小天和最大天数
     */
    private int mMinYearDay, mMaxYearDay;

    /**
     * 日历卡的项高度
     */
    private int mCalendarItemHeight;

    /**
     * 今天的日子
     */
    private Calendar mCurrentDate;

    private boolean mMonthViewScrollable, mWeekViewScrollable;

    /**
     * 当前月份和周视图的item位置
     */
    int mCurrentMonthViewItem;

    /**
     * 标记的日期,数量巨大，请使用这个
     */
    Map<String, Calendar> mSchemeDatesMap;

    /**
     * 日期选中监听
     */
    CalendarView.OnCalendarSelectListener mCalendarSelectListener;

    /**
     * 内部日期切换监听，用于内部更新计算
     */
    CalendarView.OnInnerDateSelectedListener mInnerListener;

    /**
     * 月份切换事件
     */
    CalendarView.OnMonthChangeListener mMonthChangeListener;

    /**
     * 周视图改变事件
     */
    CalendarView.OnWeekChangeListener mWeekChangeListener;

    /**
     * 视图改变事件
     */
    CalendarView.OnViewChangeListener mViewChangeListener;

    /**
     * 保存选中的日期
     */
    Calendar mSelectedCalendar;

    /**
     * 保存标记位置
     */
    Calendar mIndexCalendar;

    CalendarViewDelegate(Context context, @Nullable AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CalendarView);

        LunarCalendar.init(context);

        mMonthViewClassPath = array.getString(R.styleable.CalendarView_month_view);
        mWeekViewClassPath = array.getString(R.styleable.CalendarView_week_view);

        mMonthViewScrollable = array.getBoolean(R.styleable.CalendarView_month_view_scrollable, true);
        mWeekViewScrollable = array.getBoolean(R.styleable.CalendarView_week_view_scrollable, true);

        mWeekStart = array.getInt(R.styleable.CalendarView_week_start_with, WEEK_START_WITH_SUN);
        isShowLunar = array.getBoolean(R.styleable.CalendarView_is_show_lunar, false);

        mMinYear = array.getInt(R.styleable.CalendarView_min_year, 1971);
        mMaxYear = array.getInt(R.styleable.CalendarView_max_year, 2055);
        mMinYearMonth = array.getInt(R.styleable.CalendarView_min_year_month, 1);
        mMaxYearMonth = array.getInt(R.styleable.CalendarView_max_year_month, 12);
        mMinYearDay = array.getInt(R.styleable.CalendarView_min_year_day, 1);
        mMaxYearDay = array.getInt(R.styleable.CalendarView_max_year_day, -1);

        mCalendarItemHeight = (int) array.getDimension(R.styleable.CalendarView_calendar_height, CalendarUtil.dipToPx(context, 40));

        if (mMinYear <= MIN_YEAR) {
            mMinYear = 1971;
        }
        if (mMaxYear >= MAX_YEAR) {
            mMaxYear = 2055;
        }
        array.recycle();
        init();
    }

    private void init() {
        mCurrentDate = new Calendar();
        Date d = new Date();
        mCurrentDate.setYear(CalendarUtil.getDate("yyyy", d));
        mCurrentDate.setMonth(CalendarUtil.getDate("MM", d));
        mCurrentDate.setDay(CalendarUtil.getDate("dd", d));
        mCurrentDate.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(mCurrentDate);
        setRange(mMinYear, mMinYearMonth, mMaxYear, mMaxYearMonth);

        if (TextUtils.isEmpty(mMonthViewClassPath) || TextUtils.isEmpty(mWeekViewClassPath)) {
            mMonthViewClass = SimpleMonthView.class;
            mWeekViewClass = SimpleWeekView.class;
            return;
        }
        try {
            mMonthViewClass = Class.forName(mMonthViewClassPath);
            mWeekViewClass = Class.forName(mWeekViewClassPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setRange(int minYear, int minYearMonth,
                          int maxYear, int maxYearMonth) {
        this.mMinYear = minYear;
        this.mMinYearMonth = minYearMonth;
        this.mMaxYear = maxYear;
        this.mMaxYearMonth = maxYearMonth;
        if (this.mMaxYear < mCurrentDate.getYear()) {
            this.mMaxYear = mCurrentDate.getYear();
        }
        if (this.mMaxYearDay == -1) {
            this.mMaxYearDay = CalendarUtil.getMonthDaysCount(this.mMaxYear, mMaxYearMonth);
        }
        int y = mCurrentDate.getYear() - this.mMinYear;
        mCurrentMonthViewItem = 12 * y + mCurrentDate.getMonth() - this.mMinYearMonth;
    }

    Class<?> getMonthViewClass() {
        return mMonthViewClass;
    }

    Class<?> getWeekViewClass() {
        return mWeekViewClass;
    }

    public boolean isShowLunar() {
        return isShowLunar;
    }

    public void setShowLunar(boolean showLunar) {
        isShowLunar = showLunar;
    }

    int getMinYear() {
        return mMinYear;
    }

    int getMaxYear() {
        return mMaxYear;
    }

    int getCalendarItemHeight() {
        return mCalendarItemHeight;
    }

    int getMinYearMonth() {
        return mMinYearMonth;
    }

    int getMaxYearMonth() {
        return mMaxYearMonth;
    }

    boolean isMonthViewScrollable() {
        return mMonthViewScrollable;
    }

    boolean isWeekViewScrollable() {
        return mWeekViewScrollable;
    }

    int getWeekStart() {
        return mWeekStart;
    }

    Calendar getCurrentDay() {
        return mCurrentDate;
    }

    void clearSelectedScheme() {
        mSelectedCalendar.clearScheme();
    }

    int getMinYearDay() {
        return mMinYearDay;
    }

    int getMaxYearDay() {
        return mMaxYearDay;
    }

    Calendar createCurrentDate() {
        Calendar calendar = new Calendar();
        calendar.setYear(mCurrentDate.getYear());
        calendar.setWeek(mCurrentDate.getWeek());
        calendar.setMonth(mCurrentDate.getMonth());
        calendar.setDay(mCurrentDate.getDay());
        calendar.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final Calendar getMinRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(mMinYear);
        calendar.setMonth(mMinYearMonth);
        calendar.setDay(mMinYearDay);
        calendar.setCurrentDay(calendar.equals(mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final Calendar getMaxRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(mMaxYear);
        calendar.setMonth(mMaxYearMonth);
        calendar.setDay(mMaxYearDay);
        calendar.setCurrentDay(calendar.equals(mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    /**
     * 添加事件标记，来自Map
     */
    final void addSchemesFromMap(List<Calendar> mItems) {
        if (mSchemeDatesMap == null || mSchemeDatesMap.size() == 0) {
            return;
        }
        for (Calendar a : mItems) {
            if (mSchemeDatesMap.containsKey(a.toString())) {
                Calendar d = mSchemeDatesMap.get(a.toString());
                a.setScheme(TextUtils.isEmpty(d.getScheme()) ? "" : d.getScheme());
            } else {
                a.setScheme("");
            }
        }
    }
}
