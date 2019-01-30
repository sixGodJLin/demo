package com.example.linj.myapplication.alarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.linj.myapplication.HomeActivity;

import java.util.Objects;

/**
 * @author JLin
 * @date 2018/10/31
 */
public class AlarmReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("AlarmReceiver " + "onReceive " + "----1111111");
        if (Objects.equals(intent.getAction(), ACTION)) {
            Intent i = new Intent(context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

        Intent service = new Intent(context, AlarmService.class);
        context.startService(service);
    }

}
