package com.example.linj.myapplication.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2018/12/24
 * @describe 自定义可拓展的自定义布局
 * <ExpandView
 *      app:expand_content_view="自定义尾部id"
 *      app:expand_head_view="自定义头部的id"
 *     <View
 *          android:id="@+id/自定义头部id" />
 *     <View
 *          android:id="@+id/自定义尾部id" />
 * </ExpandView>
 */
public class ExpandView extends LinearLayout {
    private ObjectAnimator rotationAnimator;        // 旋转动画
    private int origHeight;

    private int headViewId;
    private int contentViewId;

    private View contentView;
    private View ivFold;

    private boolean isAnimator = false;

    public ExpandView(Context context) {
        this(context, null);
    }

    public ExpandView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandView);

        headViewId = typedArray.getResourceId(R.styleable.ExpandView_expand_head_view, 0);
        contentViewId = typedArray.getResourceId(R.styleable.ExpandView_expand_content_view, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View headView = findViewById(headViewId);
        ivFold = headView.findViewById(R.id.iv_fold);
        contentView = findViewById(contentViewId);
        headView.setOnClickListener(v -> {
            toggle();
        });
    }

    public void toggle() {
        if (!isAnimator) {
            if (View.VISIBLE == contentView.getVisibility()) {
                gone(contentView);// 布局隐藏
                getRotationAnimator(ivFold, 0f, 180f).start();
            } else {
                expand(contentView);// 展开
                getRotationAnimator(ivFold, -180f, 0f).start();
            }
        }
    }

    private ObjectAnimator getRotationAnimator(@Nullable Object target, float... value) {
        if (rotationAnimator == null) {
            rotationAnimator = new ObjectAnimator().setDuration(500);
        }
        rotationAnimator.setPropertyName("rotation");
        rotationAnimator.setTarget(target);
        rotationAnimator.setFloatValues(value);
        return rotationAnimator;
    }

    private void gone(final View view) {
        origHeight = view.getHeight();
        isAnimator = true;

        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                isAnimator = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {

            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(arg0 -> {
            int value = (int) arg0.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            layoutParams.height = value;
            v.setLayoutParams(layoutParams);
        });
        return animator;
    }

    private void expand(View v) {
        v.setVisibility(View.VISIBLE);
        isAnimator = true;

        ValueAnimator animator = createDropAnimator(v, 0, origHeight);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimator = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {

            }
        });
        animator.start();
    }
}
