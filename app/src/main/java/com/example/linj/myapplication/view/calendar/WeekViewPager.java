package com.example.linj.myapplication.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 周视图滑动ViewPager
 *
 * @author JLin
 */

public final class WeekViewPager extends ViewPager {
    private int mWeekCount;
    private CalendarViewDelegate mDelegate;

    /**
     * 日历布局，需要在日历下方放自己的布局
     */
    CalendarLayout mParentLayout;

    /**
     * 是否使用滚动到某一天
     */
    private boolean isUsingScrollToCalendar = false;

    public WeekViewPager(Context context) {
        this(context, null);
    }

    public WeekViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void setup(CalendarViewDelegate delegate) {
        this.mDelegate = delegate;
        init();
    }

    private void init() {
        mWeekCount = CalendarUtil.getWeekCountBetweenBothCalendar(
                mDelegate.getMinYear(),
                mDelegate.getMinYearMonth(),
                mDelegate.getMinYearDay(),
                mDelegate.getMaxYear(),
                mDelegate.getMaxYearMonth(),
                mDelegate.getMaxYearDay(),
                mDelegate.getWeekStart());
        setAdapter(new WeekViewPagerAdapter());
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (getVisibility() != VISIBLE) {
                    isUsingScrollToCalendar = false;
                    return;
                }
                if (isUsingScrollToCalendar) {
                    isUsingScrollToCalendar = false;
                    return;
                }
                BaseWeekView view = findViewWithTag(position);
                if (view != null) {
                    view.performClickCalendar(mDelegate.mIndexCalendar, !isUsingScrollToCalendar);
                    if (mDelegate.mWeekChangeListener != null) {
                        mDelegate.mWeekChangeListener.onWeekChange(getCurrentWeekCalendars());
                    }
                }
                isUsingScrollToCalendar = false;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 获取当前周数据
     *
     * @return 获取当前周数据
     */
    List<Calendar> getCurrentWeekCalendars() {
        List<Calendar> calendars = CalendarUtil.getWeekCalendars(mDelegate.mIndexCalendar,
                mDelegate);
        mDelegate.addSchemesFromMap(calendars);
        return calendars;
    }

    /**
     * 更新任意一个选择的日期
     */
    void updateSelected(Calendar calendar, boolean smoothScroll) {
        int position = CalendarUtil.getWeekFromCalendarStartWithMinCalendar(calendar,
                mDelegate.getMinYear(),
                mDelegate.getMinYearMonth(),
                mDelegate.getMinYearDay(),
                mDelegate.getWeekStart()) - 1;
        int curItem = getCurrentItem();
        isUsingScrollToCalendar = curItem != position;
        setCurrentItem(position, smoothScroll);
        BaseWeekView view = findViewWithTag(position);
        if (view != null) {
            view.setSelectedCalendar(calendar);
            view.invalidate();
        }
    }

    /**
     * 更新标记日期
     */
    void updateScheme() {
        for (int i = 0; i < getChildCount(); i++) {
            BaseWeekView view = (BaseWeekView) getChildAt(i);
            view.update();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDelegate.isWeekViewScrollable() && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDelegate.isWeekViewScrollable() && super.onInterceptTouchEvent(ev);
    }

    /**
     * 周视图的高度应该与日历项的高度一致
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mDelegate.getCalendarItemHeight(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 周视图切换
     */
    private class WeekViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mWeekCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Calendar calendar = CalendarUtil.getFirstCalendarStartWithMinCalendar(mDelegate.getMinYear(),
                    mDelegate.getMinYearMonth(),
                    mDelegate.getMinYearDay(),
                    position + 1,
                    mDelegate.getWeekStart());
            BaseWeekView view;
            try {
                Constructor constructor = mDelegate.getWeekViewClass().getConstructor(Context.class);
                view = (BaseWeekView) constructor.newInstance(getContext());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            view.mParentLayout = mParentLayout;
            view.setup(mDelegate);
            view.setup(calendar);
            view.setTag(position);
            view.setSelectedCalendar(mDelegate.mSelectedCalendar);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            BaseWeekView view = (BaseWeekView) object;
            if (view == null) {
                return;
            }
            container.removeView(view);
        }

    }
}
