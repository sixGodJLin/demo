package com.example.linj.myapplication.broadcast;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.linj.myapplication.R;

/**
 * @author JLin
 */
public class BroadCastActivity extends Activity {
    BroadCast broadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("***");

        broadCast = new BroadCast();

        registerReceiver(broadCast, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCast);
    }
}
