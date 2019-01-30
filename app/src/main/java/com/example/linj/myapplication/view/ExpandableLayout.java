package com.example.linj.myapplication.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.linj.myapplication.R;


/**
 * 可展开的布局容器，只需要在布局中添加相应的展开子布局即可，子布局可为任何view
 * Created by shenhua on 2019/1/15.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
public class ExpandableLayout extends LinearLayout {
    private String title;
    private float titleSize;
    private int titleColor;
    private int arrowIcon;
    private int drawable;
    private int contentViewId;
    private View contentView;

    public ExpandableLayout(Context context) {
        this(context, null);
    }

    public ExpandableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBackgroundColor(Color.WHITE);
        setOrientation(VERTICAL);
        setLayoutTransition(new LayoutTransition());
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.divider_linear));
        setShowDividers(SHOW_DIVIDER_MIDDLE);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout);
        titleColor = typedArray.getColor(R.styleable.ExpandableLayout_titleColor, ContextCompat.getColor(context, R.color.colorPrimary));
        titleSize = dip2px(context, typedArray.getDimension(R.styleable.ExpandableLayout_titleSize, 14));
        title = typedArray.getString(R.styleable.ExpandableLayout_title);
        arrowIcon = typedArray.getResourceId(R.styleable.ExpandableLayout_arrowIcon, R.drawable.vector_ic_arrow_right_gray);
        drawable = typedArray.getResourceId(R.styleable.ExpandableLayout_drawable, 0);

        contentViewId = typedArray.getResourceId(R.styleable.ExpandableLayout_content_view, 0);
        typedArray.recycle();

        addTitle(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = findViewById(contentViewId);
    }

    private void addTitle(Context context) {
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setPadding(32, 32, 32, 32);
        titleView.setTextColor(titleColor);
        titleView.setGravity(Gravity.CENTER);
        titleView.setCompoundDrawablePadding(10);
        titleView.setTextSize(titleSize);
        if (drawable > 0) {
            titleView.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(context, drawable), null, null, null);
        }
        constraintLayout.addView(titleView);

        final ImageView arrowImage = new ImageView(context);
        arrowImage.setImageResource(arrowIcon);
        arrowImage.setRotation(-90);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(30, 30);
        lp.setMargins(32, 32, 32, 32);
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        constraintLayout.addView(arrowImage, lp);

        constraintLayout.setOnClickListener(v -> {
            if (contentView != null) {
                if (!isAnimate) {
                    if (View.VISIBLE == contentView.getVisibility()) {
                        gone();
                        ViewCompat.animate(arrowImage).rotation(90).setDuration(2000).start();
                    } else {
                        expand();
                        ViewCompat.animate(arrowImage).rotation(-90).setDuration(2000).start();
                    }
                }
            }
        });
        addView(constraintLayout);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue / scale + 0.5f);
    }

    private int origHeight;
    private boolean isAnimate = false;

    private void gone() {
        origHeight = contentView.getHeight();
        isAnimate = true;

        ValueAnimator animator = createDropAnimator(origHeight, 0);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
                contentView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(arg0 -> {
            int value = (int) arg0.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.height = value;
            contentView.setLayoutParams(layoutParams);
        });
        return animator;
    }

    private void expand() {
        isAnimate = true;
        contentView.setVisibility(View.VISIBLE);

        ValueAnimator animator = createDropAnimator(0, origHeight);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
                initContentView();
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }
        });
        animator.start();
    }

    private void initContentView() {
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        contentView.setLayoutParams(layoutParams);
    }
}
