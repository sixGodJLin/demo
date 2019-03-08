package com.example.linj.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author JLin
 * @date 2019/3/7
 */
public class MyService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyService2 " + "onBind " + "----");
        return new MyBinder();
    }

    class MyBinder extends Binder {
        void show() {
            System.out.println("MyBind " + "show " + "----");
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("MyService2 " + "onUnbind " + "----");
        return super.onUnbind(intent);
    }
}
