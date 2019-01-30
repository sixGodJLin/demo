package com.example.linj.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.linj.myapplication.view.EfficacyView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EfficacyActivity extends AppCompatActivity {
    @BindView(R.id.efficacyView)
    EfficacyView efficacyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efficacy);
        ButterKnife.bind(this);
        efficacyView.setMaxValue(31);
        String[] strings = {"第一周", "第二周", "第三周"};
        int[] value = {31, 10, 20};
        efficacyView.setXDescription(strings, value);
    }
}
