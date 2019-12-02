package com.example.linj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.linj.myapplication.view.SwipeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 类似左滑删除的东西... 还未完成
 */
public class SwipeActivity extends Activity {
    SwipeView swipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        swipeView = new SwipeView(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addContentView(swipeView, params);
    }
}
