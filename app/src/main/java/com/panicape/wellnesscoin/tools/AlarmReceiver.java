package com.panicape.wellnesscoin.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.ContextCompat;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class AlarmReceiver extends BroadcastReceiver {

    //Methods

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, NotificationService.class);
        service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        ContextCompat.startForegroundService(context, service1 );
    }
}
