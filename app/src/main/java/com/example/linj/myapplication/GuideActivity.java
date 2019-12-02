package com.example.linj.myapplication;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.linj.myapplication.utils.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 引导界面
 */
public class GuideActivity extends Activity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.guide_line)
    View guideLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        List<Integer> resId = new ArrayList<>();

        resId.add(R.drawable.img1);
        resId.add(R.drawable.img2);
        resId.add(R.drawable.img3);


//        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) getResources().getDimension(R.dimen.px48), (int) getResources().getDimension(R.dimen.px114));
//        valueAnimator.addUpdateListener(animation -> {
//            int value = (int) animation.getAnimatedValue();
//            ViewGroup.LayoutParams layoutParams = guideLine.getLayoutParams();
//            layoutParams.width = value;
//            guideLine.setLayoutParams(layoutParams);
//        });
//        valueAnimator.start();


        GuideAdapter adapter = new GuideAdapter(getApplicationContext(), resId);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftMargin = getResources().getDimension(R.dimen.px66) * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) guideLine.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                guideLine.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                System.out.println("GuideActivity " + "onPageScrollStateChanged " + "----" + i);
            }
        });
    }
}
