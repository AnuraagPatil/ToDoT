package com.example.anuraag.todot;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by anuraag on 4/2/18.
 */

public class Alarm extends BroadcastReceiver {

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {

        //sound the alarm
        mp= MediaPlayer.create(context, R.raw.alarm);
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

        Intent notify = new Intent(context, Notification.class);
        PendingIntent pi = PendingIntent.getActivity(context,0,notify,0);
        mBuilder.setContentIntent(pi);

        android.app.Notification notification=mBuilder.build();
        NotificationManager manager= (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void setAlarm(Context context, long timeInterval){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        //manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+timeInterval,2000, pi);
        manager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+timeInterval,pi);

        Toast.makeText(context, "Remainder Set "+timeInterval/1000, Toast.LENGTH_SHORT).show();
    }

    public static void cancelAlarm(Context context){

        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);

        Toast.makeText(context, "Remainder removed", Toast.LENGTH_SHORT).show();
    }
}
