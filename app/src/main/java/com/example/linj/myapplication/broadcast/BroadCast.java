package com.example.linj.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author JLin
 * @date 2019/3/11
 */
public class BroadCast extends BroadcastReceiver {
    public static final String TAG = "BroadCast";

    private static String ACTION_CAST = "com.JLin.broadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("BroadCast " + "onReceive " + "----");
        if (ACTION_CAST.equals(intent.getAction())) {
            Log.d(TAG, "onReceive: " + "1234567");
        }
    }
}
