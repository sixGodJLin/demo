package com.example.linj.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2019/1/21
 */
public class EfficacyView extends LinearLayout {
    /**
     * x轴 横坐标据线条
     */
    private Paint lineXPaint = new Paint();

    /**
     * y轴 指标数据线条
     */
    private Paint lineYPaint = new Paint();

    /**
     * title iconId
     */
    private Drawable icon;

    /**
     * 图表的标题
     */
    private String title;

    /**
     * 图表的单位
     */
    private String unit;

    /**
     * y轴 纵坐标 字体画笔
     */
    private Paint yAxisTextPaint = new Paint();

    /**
     * y轴 Value 条形图
     */
    private Paint barPaint = new Paint();

    /**
     * 条状图 value 字体颜色
     */
    private Paint valueTextPaint = new Paint();

    /**
     * y轴条状图颜色
     */
    private int barColorId;

    /**
     * 条状图 范围高度
     */
    private int mHeight;

    /**
     * 控件宽度
     */
    private int mWidth;

    /**
     * y轴间距
     */
    private float spacingY;

    /**
     * x轴处在的高度
     */
    private float xAxisHeight;

    /**
     * y轴值的最大值（传入的值的最大值）
     */
    private int maxValue = 30;

    /**
     * 横坐标的描述
     */
    private String[] abscissa;

    /**
     * 条状图的值
     */
    private int[] value;


    public EfficacyView(Context context) {
        this(context, null);
    }

    public EfficacyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint({"Recycle", "CustomViewStyleable"})
    public EfficacyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
        // 获取自定义样式
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EfficacyViewStyle);
        if (ta != null) {
            title = ta.getString(R.styleable.EfficacyViewStyle_efficacy_title);
            icon = ta.getDrawable(R.styleable.EfficacyViewStyle_efficacy_icon_id);
            unit = ta.getString(R.styleable.EfficacyViewStyle_efficacy_unit);
            barColorId = ta.getInteger(R.styleable.EfficacyViewStyle_efficacy_bar_color, Color.WHITE);
            ta.recycle();
        }

        lineXPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lineXPaint.setColor(Color.parseColor("#d9e4f2"));
        lineXPaint.setStrokeWidth(2);

        lineYPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        lineYPaint.setColor(Color.parseColor("#66d9e4f2"));
        lineYPaint.setStrokeWidth(2);

        barPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        barPaint.setColor(barColorId);

        valueTextPaint.setTextSize(dipToPx(getContext(), 13));
        valueTextPaint.setTextAlign(Paint.Align.CENTER);
        valueTextPaint.setColor(barColorId);
        valueTextPaint.setAntiAlias(true);

        yAxisTextPaint.setTextSize(dipToPx(getContext(), 13));
        yAxisTextPaint.setTextAlign(Paint.Align.CENTER);
        yAxisTextPaint.setColor(Color.parseColor("#999999"));
        yAxisTextPaint.setAntiAlias(true);

        addView(initTitle(context));
    }

    @SuppressLint("SetTextI18n")
    private View initTitle(Context context) {
        View view = inflate(context, R.layout.item_efficacy_title, null);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.px80));
        view.setLayoutParams(layoutParams);

        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvUnit = view.findViewById(R.id.tv_unit);

        if (icon != null) {
            ivIcon.setVisibility(VISIBLE);
            ivIcon.setBackground(icon);
        } else {
            ivIcon.setVisibility(GONE);
        }
        tvTitle.setText(title);
        tvUnit.setText("（" + unit + "）");

        return view;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight() - (int) getContext().getResources().getDimension(R.dimen.px186);
        mWidth = getMeasuredWidth();
        xAxisHeight = getMeasuredHeight() - (int) getContext().getResources().getDimension(R.dimen.px74);
        spacingY = mHeight / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXAxis(canvas);
        drawYAxis(canvas);
    }

    /**
     * 画Y轴
     *
     * @param canvas canvas
     */
    private void drawYAxis(Canvas canvas) {
        int startX = (int) getContext().getResources().getDimension(R.dimen.px44);
        Paint.FontMetrics fontMetrics = yAxisTextPaint.getFontMetrics();
        canvas.drawText("0", startX, xAxisHeight + fontMetrics.bottom, yAxisTextPaint);
        canvas.drawText(String.valueOf(maxValue), startX, xAxisHeight - (3 * spacingY) + fontMetrics.bottom, yAxisTextPaint);
        canvas.drawText(String.valueOf((maxValue / 3) * 2), startX, xAxisHeight - (2 * spacingY) + fontMetrics.bottom, yAxisTextPaint);
        canvas.drawText(String.valueOf(maxValue / 3), startX, xAxisHeight - spacingY + fontMetrics.bottom, yAxisTextPaint);

        canvas.drawLine((int) getContext().getResources().getDimension(R.dimen.px66), xAxisHeight - (3 * spacingY),
                mWidth - (int) getContext().getResources().getDimension(R.dimen.px32), xAxisHeight - (3 * spacingY), lineYPaint);
        canvas.drawLine((int) getContext().getResources().getDimension(R.dimen.px66), xAxisHeight - (2 * spacingY),
                mWidth - (int) getContext().getResources().getDimension(R.dimen.px32), xAxisHeight - (2 * spacingY), lineYPaint);
        canvas.drawLine((int) getContext().getResources().getDimension(R.dimen.px66), xAxisHeight - spacingY,
                mWidth - (int) getContext().getResources().getDimension(R.dimen.px32), xAxisHeight - spacingY, lineYPaint);
    }

    /**
     * 画X轴的横线 以及 条状图
     *
     * @param canvas canvas
     */
    private void drawXAxis(Canvas canvas) {
        int startX = (int) getContext().getResources().getDimension(R.dimen.px66);
        // 条状图单位高度
        float perHeight = (float) mHeight / maxValue;

        canvas.drawLine(startX, xAxisHeight, mWidth - (int) getContext().getResources().getDimension(R.dimen.px32), xAxisHeight, lineXPaint);

        // 柱状图宽度
        float columViewWidth = mWidth - (int) getContext().getResources().getDimension(R.dimen.px98);
        // 计算 柱状图之间的间距
        float spacingX = (columViewWidth - (abscissa.length * (int) getContext().getResources().getDimension(R.dimen.px40))) / (abscissa.length + 1);

        for (int i = 0; i < abscissa.length; i++) {
            canvas.drawText(abscissa[i],
                    startX + (i * (int) getContext().getResources().getDimension(R.dimen.px40)) + (i + 1) * spacingX,
                    xAxisHeight + (int) getContext().getResources().getDimension(R.dimen.px32),
                    yAxisTextPaint);

            canvas.drawRect(startX + ((i + 1) * spacingX) + (i * (int) getContext().getResources().getDimension(R.dimen.px40)) - (int) getContext().getResources().getDimension(R.dimen.px20),
                    xAxisHeight - (perHeight * value[i]),
                    startX + ((i + 1) * spacingX) + (i * (int) getContext().getResources().getDimension(R.dimen.px40)) + (int) getContext().getResources().getDimension(R.dimen.px20),
                    xAxisHeight,
                    barPaint);

            canvas.drawText(String.valueOf(value[i]),
                    startX + (i * (int) getContext().getResources().getDimension(R.dimen.px40)) + (i + 1) * spacingX,
                    xAxisHeight - (perHeight * (value[i] + 1)),
                    valueTextPaint);
        }
    }

    /**
     * 设置最大值
     *
     * @param maxValue 最大值
     */
    public void setMaxValue(int maxValue) {
        if (maxValue % 3 != 0) {
            maxValue += (3 - maxValue % 3);
        }
        this.maxValue = maxValue;
        invalidate();
    }

    /**
     * 传入横坐标的值
     *
     * @param abscissa 横坐标
     */
    public void setXDescription(String[] abscissa, int[] value) {
        this.abscissa = abscissa;
        this.value = value;
        invalidate();
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
