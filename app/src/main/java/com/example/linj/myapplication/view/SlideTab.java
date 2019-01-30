package com.example.linj.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JLin
 * @date 2018/11/9
 */
public class SlideTab extends View {
    String TAG = "SlidingTab";
    private int mTextSize;          //文本的字体大小
    private int mColorTextDef;      // 默认文本的颜色
    private int mColorDef;          // 线段和圆圈颜色
    private int mColorSelected;     //选中的字体和圆圈颜色
    private int mLineHight;         //基准线高度
    private int mCircleHight;       //圆圈的高度（直径）
    private int mCircleSelStroke;   //被选中圆圈（空心）的粗细
    private int mMarginTop;         //圆圈和文字之间的距离
    private String[] tabNames;      //需要绘制的文字

    /**
     * 下面需要计算
     */
    private float splitLengh;       //每一段横线长度
    private int textStartY;         //文本绘制的Y轴坐标
    private List<Rect> mBounds;     //保存文本的量的结果

    private int selectedIndex = 0;      //当前选中序号

    private Paint mTextPaint;      //绘制文字的画笔
    private Paint mLinePaint;      //绘制基准线的画笔
    private Paint mCirclePaint;    //绘制基准线上灰色圆圈的画笔
    private Paint mCircleSelPaint; //绘制被选中位置的蓝色圆圈的画笔

    public SlideTab(Context context) {
        this(context, null);
    }

    public SlideTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化属性
        tabNames = new String[]{"tab1", "tab2", "tab3", "tab4"};

        mColorTextDef = Color.GRAY;
        mColorSelected = Color.BLUE;
        mColorDef = Color.argb(255, 234, 234, 234);   //#EAEAEA
        mTextSize = 20;

        mLineHight = 5;
        mCircleHight = 20;
        mCircleSelStroke = 10;
        mMarginTop = 50;

        mLinePaint = new Paint();
        mCirclePaint = new Paint();
        mTextPaint = new Paint();
        mCircleSelPaint = new Paint();

        mLinePaint.setColor(mColorDef);
        mLinePaint.setStyle(Paint.Style.FILL);//设置填充
        mLinePaint.setStrokeWidth(mLineHight);//笔宽像素
        mLinePaint.setAntiAlias(true);//锯齿不显示

        mCirclePaint.setColor(mColorDef);
        mCirclePaint.setStyle(Paint.Style.FILL);//设置填充
        mCirclePaint.setStrokeWidth(1);//笔宽像素
        mCirclePaint.setAntiAlias(true);//锯齿不显示
        mCircleSelPaint.setColor(mColorSelected);
        mCircleSelPaint.setStyle(Paint.Style.STROKE);    //空心圆圈
        mCircleSelPaint.setStrokeWidth(mCircleSelStroke);
        mCircleSelPaint.setAntiAlias(true);

        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mColorTextDef);
        mLinePaint.setAntiAlias(true);

        measureText();
    }

    /**
     * measure the text bounds by paint
     */
    private void measureText() {
        mBounds = new ArrayList<>();
        for (String name : tabNames) {
            Rect mBound = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), mBound);
            mBounds.add(mBound);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initConstant();
    }

    private void initConstant() {
        int lineLengh = getWidth() - getPaddingLeft() - getPaddingRight() - mCircleHight;
        splitLengh = lineLengh / (tabNames.length - 1);
        textStartY = mCircleHight + mMarginTop + getPaddingTop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画灰色基准线
        canvas.drawLine(mCircleHight / 2, mCircleHight / 2, getWidth() - mCircleHight / 2, mCircleHight / 2, mLinePaint);

        float centerY = mCircleHight / 2;
        for (int i = 0; i < tabNames.length; i++) {
            float centerX = mCircleHight / 2 + (i * splitLengh);
            canvas.drawCircle(centerX, centerY, mCircleHight / 2, mCirclePaint);

            mTextPaint.setColor(mColorTextDef);
            if (selectedIndex == i) {
                //画选中位置的蓝色圆圈
                mCircleSelPaint.setStrokeWidth(mCircleSelStroke);
                mCircleSelPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(centerX, centerY, (mCircleHight - mCircleSelStroke) / 2, mCircleSelPaint);
                mTextPaint.setColor(mColorSelected);
            }

            //绘制文字
            float startX;
            if (i == 0) {
                startX = 0;
            } else if (i == tabNames.length - 1) {
                startX = getWidth() - mBounds.get(i).width();
            } else {
                startX = centerX - (mBounds.get(i).width() / 2);
            }
            canvas.drawText(tabNames[i], startX, textStartY, mTextPaint);
        }
    }
}
