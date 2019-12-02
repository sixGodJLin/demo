package com.example.linj.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 可拓展界面
 */
public class ExpandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
    }
}
