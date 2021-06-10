package com.example.linj.myapplication.myService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 * @date 2019/3/7
 */
public class MyService1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyService1 " + "onBind " + "----");
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("MyService1 " + "onCreate " + "----");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutors = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutors.scheduleWithFixedDelay(() ->
                System.out.println("MyService1 " + "run " + "----"), 1000, 1000, TimeUnit.MILLISECONDS);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("MyService1 " + "onDestroy " + "----");
        super.onDestroy();
    }
}
