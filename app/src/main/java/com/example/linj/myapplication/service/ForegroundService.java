package com.example.linj.myapplication.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.linj.myapplication.HomeActivity;
import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2019/3/7
 */
public class ForegroundService extends Service {
    private static final String ID_CHANNEL = "My Notification";
    private NotificationManager manager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
    }

    private void showNotification() {
        System.out.println("ForegroundService " + "showNotification " + "----");
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle("My Notification")
                .setAutoCancel(true) // 设置点击后自动消失
                .setContentText("Content");

        Intent notifyIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID_CHANNEL, ID_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Notification notification = builder.build();
        manager.notify(0, notification);
        startForeground(0, notification);
    }

    @Override
    public void onDestroy() {
        if (manager != null) {
            manager.cancel(0);
        }
        stopForeground(true);
        super.onDestroy();
    }
}
