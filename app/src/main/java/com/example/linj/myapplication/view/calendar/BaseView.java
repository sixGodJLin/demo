package com.example.linj.myapplication.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import static com.example.linj.myapplication.view.calendar.CalendarUtil.dipToPx;

/**
 * 视图基类
 * @author JLin
 */

public abstract class BaseView extends View implements View.OnClickListener, View.OnLongClickListener {

    CalendarViewDelegate mDelegate;

    /**
     * 日历文本日期的笔
     */
    protected Paint mTextPaint = new Paint();

    /**
     * 当前日期文本颜色画笔
     */
    protected Paint mCurDayTextPaint = new Paint();

    /**
     * 当前日期背景圆形颜色画笔
     */
    protected Paint mCurCircleDayPaint = new Paint();

    /**
     * 被选择的日期背景色
     */
    protected Paint mSelectedPaint = new Paint();

    /**
     * 日历文本日期的笔
     */
    protected Paint mLunarTextPaint = new Paint();

    /**
     * 日历文本日期的笔
     */
    protected Paint mCurLunarTextPaint = new Paint();

    /**
     * 上角标圆形画笔
     */
    protected Paint mSchemeCirclePaint = new Paint();

    /**
     * 标记的文本画笔
     */
    protected Paint mSchemeTextPaint = new Paint();

    /**
     * 选中的文本画笔
     */
    protected Paint mSelectTextPaint = new Paint();

    /**
     * 是否显示农历时间、节日信息
     */
    protected boolean isShowLunar;

    /**
     * 日历布局，需要在日历下方放自己的布局
     */
    CalendarLayout mParentLayout;

    /**
     * 日历项
     */
    List<Calendar> mItems;

    /**
     * 每一项的高度
     */
    protected int mItemHeight;

    /**
     * 每一项的宽度
     */
    protected int mItemWidth;

    /**
     * Text的基线
     */
    protected float mTextBaseLine;

    /**
     * 点击的x、y坐标
     */
    float mX, mY;

    /**
     * 是否点击
     */
    boolean isClick = true;

    /**
     * 字体大小
     */
    static final int TEXT_SIZE = 14;
    static final int LUNAR_TEXT_SIZE = 10;

    /**
     * 当前点击项
     */
    int mCurrentItem = -1;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    /**
     * 初始化配置
     *
     * @param context context
     */
    private void initPaint(Context context) {
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xFF666666);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(dipToPx(context, TEXT_SIZE));

        mSelectTextPaint.setAntiAlias(true);
        mSelectTextPaint.setStyle(Paint.Style.FILL);
        mSelectTextPaint.setTextAlign(Paint.Align.CENTER);
        mSelectTextPaint.setColor(0xffed5353);
        mSelectTextPaint.setFakeBoldText(true);
        mSelectTextPaint.setTextSize(dipToPx(context, TEXT_SIZE));

        mSelectedPaint.setColor(0xFF9fcfff);
        mSelectedPaint.setAntiAlias(true);
        mSelectedPaint.setStyle(Paint.Style.FILL);

        mCurDayTextPaint.setAntiAlias(true);
        mCurDayTextPaint.setTextAlign(Paint.Align.CENTER);
        mCurDayTextPaint.setColor(0xffffffff);
        mCurDayTextPaint.setFakeBoldText(true);
        mCurDayTextPaint.setTextSize(dipToPx(context, TEXT_SIZE));

        mCurCircleDayPaint.setAntiAlias(true);
        mCurCircleDayPaint.setStyle(Paint.Style.FILL);
        mCurCircleDayPaint.setColor(0xFF358de5);

        mLunarTextPaint.setAntiAlias(true);
        mLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        mLunarTextPaint.setColor(0xFF999999);
        mLunarTextPaint.setFakeBoldText(true);
        mLunarTextPaint.setTextSize(dipToPx(context, LUNAR_TEXT_SIZE));

        mCurLunarTextPaint.setAntiAlias(true);
        mCurLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        mCurLunarTextPaint.setColor(0xFFffffff);
        mCurLunarTextPaint.setFakeBoldText(true);
        mCurLunarTextPaint.setTextSize(dipToPx(context, LUNAR_TEXT_SIZE));

        mSchemeCirclePaint.setAntiAlias(true);
        mSchemeCirclePaint.setStyle(Paint.Style.FILL);
        mSchemeCirclePaint.setColor(0xFFf75e5a);

        mSchemeTextPaint.setTextSize(dipToPx(context, 8));
        mSchemeTextPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeTextPaint.setColor(0xffffffff);
        mSchemeTextPaint.setFakeBoldText(true);
        mSchemeTextPaint.setAntiAlias(true);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    /**
     * 初始化所有UI配置
     *
     * @param delegate delegate
     */
    void setup(CalendarViewDelegate delegate) {
        this.mDelegate = delegate;
        this.isShowLunar = delegate.isShowLunar();
        updateItemHeight();
    }

    void updateItemHeight() {
        this.mItemHeight = mDelegate.getCalendarItemHeight();
        Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
        mTextBaseLine = mItemHeight / 2 - metrics.descent + (metrics.bottom - metrics.top) / 2;
    }

    /**
     * 移除事件
     */
    final void removeSchemes() {
        for (Calendar a : mItems) {
            a.setScheme("");
        }
    }

    /**
     * 添加事件标记，来自Map
     */
    final void addSchemesFromMap() {
        if (mDelegate.mSchemeDatesMap == null || mDelegate.mSchemeDatesMap.size() == 0) {
            return;
        }
        for (Calendar a : mItems) {
            if (mDelegate.mSchemeDatesMap.containsKey(a.toString())) {
                Calendar d = mDelegate.mSchemeDatesMap.get(a.toString());
                a.setScheme(TextUtils.isEmpty(d.getScheme()) ? "" : d.getScheme());
            } else {
                a.setScheme("");
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                isClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float mDY;
                if (isClick) {
                    mDY = event.getY() - mY;
                    isClick = Math.abs(mDY) <= 50;
                }
                break;
            case MotionEvent.ACTION_UP:
                mX = event.getX();
                mY = event.getY();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 开始绘制前的钩子，这里做一些初始化的操作，每次绘制只调用一次，性能高效
     * 没有需要可忽略不实现
     * 例如：
     * 1、需要绘制圆形标记事件背景，可以在这里计算半径
     * 2、绘制矩形选中效果，也可以在这里计算矩形宽和高
     */
    protected void onPreviewHook() {
        // TODO: 2017/11/16
    }

    /**
     * 更新事件
     */
    final void update() {
        if (mDelegate.mSchemeDatesMap == null || mDelegate.mSchemeDatesMap.size() == 0) {
            removeSchemes();
            invalidate();
            return;
        }
        addSchemesFromMap();
        invalidate();
    }

    /**
     * 是否在日期范围内
     *
     * @param calendar calendar
     * @return 是否在日期范围内
     */
    protected final boolean isInRange(Calendar calendar) {
        return mDelegate != null && CalendarUtil.isCalendarInRange(calendar, mDelegate);
    }
}
