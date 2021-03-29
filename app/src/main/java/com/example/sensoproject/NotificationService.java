package com.example.sensoproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.sensoproject.App.CHANNEL_ID;


public class NotificationService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        String input1 = intent.getStringExtra("inputExtra1");
        String input2 = intent.getStringExtra("inputExtra2");
        String input3 = intent.getStringExtra("inputExtra3");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Sensor Meter")





                .setContentText("All input" )
                .setSmallIcon(R.drawable.ic_a)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(input)
                        .addLine(input1)
                        .addLine(input2)
                        .addLine(input3))
                .build();



        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

