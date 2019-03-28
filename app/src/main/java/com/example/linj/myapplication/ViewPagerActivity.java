package com.example.linj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.linj.myapplication.utils.MyPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPagerActivity extends Activity {

    @BindView(R.id.view_pager_view)
    ViewPager viewPagerView;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.main)
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getApplicationContext());
        myPagerAdapter.setMyOnClick(() -> {
            System.out.println("ViewPagerActivity " + "onCreate " + "----");
        });
        viewPagerView.setAdapter(myPagerAdapter);
        viewPagerView.setCurrentItem(300);
    }

    @OnClick({R.id.next, R.id.main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                viewPagerView.setCurrentItem(viewPagerView.getCurrentItem() + 1, true);
                break;
            case R.id.main:
                System.out.println("ViewPagerActivity " + "onViewClicked " + "----");
                break;
            default:
                break;
        }
    }
}
