package com.panicape.wellnesscoin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.panicape.wellnesscoin.tools.AlarmReceiver;

import java.util.Calendar;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class ConfigActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;

    private static final int alarmId = 1;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        alarmMgr =
                (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
    }

    public void setAlarm(Context ctx) {
        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.

        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(this, alarmId, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()    , pendingIntent);
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public void onBackPressed() {

    }
}