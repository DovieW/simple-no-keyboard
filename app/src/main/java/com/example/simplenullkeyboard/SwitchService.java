package com.example.simplenullkeyboard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;

public class SwitchService extends Service {
    public static final int NOTIFICATION_ID = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "SimpleNoKeyboard",
                    "Simple No Keyboard",
                    NotificationManager.IMPORTANCE_LOW
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent switchIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        PendingIntent pendingSwitchIntent = PendingIntent.getActivity(this, 0, switchIntent, 0);

        Notification notification = new Notification.Builder(this, "SimpleNoKeyboard")
                .setContentTitle("Simple No Keyboard")
                .setContentText("Tap to switch keyboard")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingSwitchIntent)
                .setOngoing(true)
                .build();

        startForeground(NOTIFICATION_ID, notification);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
