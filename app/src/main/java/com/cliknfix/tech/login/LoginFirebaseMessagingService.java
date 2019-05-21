package com.cliknfix.tech.login;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.splash.SplashActivity;
import com.cliknfix.tech.util.PreferenceHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class LoginFirebaseMessagingService extends FirebaseMessagingService {

    String token;

    public LoginFirebaseMessagingService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }else {
                            // Get new Instance ID token
                            token = task.getResult().getToken();
                            new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, token);
                            String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
                            Log.d("1", mLoginToken);
                            //sendNotification();
                        }
                    }
                });
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            sendNotification( remoteMessage);
//
//            Intent broadcast = new Intent();
//            broadcast.setAction("OPEN_NEW_ACTIVITY);
//                    sendBroadcast(broadcast);
//        }
    }


    @Override
    public void onNewToken(String token) {
        this.token = token;
        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, token);
        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        Log.d(TAG, "Refreshed token: " + mLoginToken);
        //sendNotification();
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    private void sendNotification(){


       /* NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);
*/
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.msg_icon)
                .setContentTitle("Notification Title")
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentText("Content goes here")
                .setContentIntent(pendingIntent)
                //.setStyle(style)
                //.setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);


        notificationManager.notify(1, notificationBuilder.build());


    }
}
