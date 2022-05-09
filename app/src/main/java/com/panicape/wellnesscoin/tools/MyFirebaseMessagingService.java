package com.panicape.wellnesscoin.tools;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * MyFirebaseMessagingService class
 *
 * @author panicape
 * @version 1.01 December 2020
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * Method onNewToken
     * @param s
     */
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Para ver el identificador del dispositivo
        Log.i("Token", "Mi token es: " + s);

    }



    /**
     *
     * @param remoteMessage
     */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {
            Log.i("RECIBIDO, title", remoteMessage.getNotification().getTitle());
            Log.i("RECIBIDO, body", remoteMessage.getNotification().getBody());
            Log.i("RECIBIDO, from", remoteMessage.getFrom());
        }
    }


}
