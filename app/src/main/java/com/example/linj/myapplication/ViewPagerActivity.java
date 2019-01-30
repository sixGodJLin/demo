package com.example.linj.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.linj.myapplication.utils.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_view)
    ViewPager viewPagerView;
    @BindView(R.id.next)
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getApplicationContext());
        ViewPager mGuideVp = findViewById(R.id.view_pager_view);
        mGuideVp.setAdapter(myPagerAdapter);

        mGuideVp.setCurrentItem(300);
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        viewPagerView.setCurrentItem(viewPagerView.getCurrentItem() + 1, true);
    }
}
