package com.example.linj.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 */
public class ScheduleActivity extends Activity {
    ScheduledThreadPoolExecutor executor;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        executor = new ScheduledThreadPoolExecutor(0);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("ScheduleActivity " + "run " + "----" + i);
            i++;
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
