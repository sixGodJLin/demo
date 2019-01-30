package com.example.linj.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linj.myapplication.alarm.AlarmReceiver;


/**
 * @author JLin
 */
public class AlarmActivity extends AppCompatActivity {
    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmReceiver = new AlarmReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        registerReceiver(alarmReceiver, filter);


        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra("msg", "这是一条测试广播");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(alarmReceiver);
        super.onDestroy();
    }
}