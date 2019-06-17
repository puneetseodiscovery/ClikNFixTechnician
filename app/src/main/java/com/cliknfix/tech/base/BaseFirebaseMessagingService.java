package com.cliknfix.tech.base;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cliknfix.tech.R;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.util.PreferenceHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class BaseFirebaseMessagingService extends FirebaseMessagingService {
    String deviceToken;

    public BaseFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate","Working");
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        deviceToken = task.getResult().getToken();
                        Log.e("Firebase Token", deviceToken);
                        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, deviceToken);
                        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
                        Log.d("1mLoginToken", mLoginToken);
           /* if(mLoginToken!= null)
                sendNotification();*/
                    }
                });
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("Data","" + remoteMessage.getData().get("message"));
        sendNotification(remoteMessage.getData().get("message"),remoteMessage.getData().get("technician_id"),remoteMessage.getData().get("user_id"),remoteMessage.getData().get("labour_rate"),remoteMessage.getData().get("user_phone"));
    }

    @Override
    public void onNewToken(String token) {
        Log.e("onNewToken","Working");
        this.deviceToken = token;
        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, token);
        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        Log.d(TAG, "Refreshed token: " + mLoginToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    private void sendNotification(String message,String techId,String userId,String labour,String phone){
        Log.e("sendNotification","Working");

        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        intent.putExtra("notificationId",10);
        intent.putExtra("technician_id",techId);
        intent.putExtra("user_id",userId);
        intent.putExtra("message",message);
        intent.putExtra("labour_rate",labour);
        intent.putExtra("user_phone",phone);
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
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle("A new job for you")
                .setAutoCancel(true)
                //.setSound(defaultSound)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                //.setStyle(style)
                //.setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .addAction(R.drawable.ic_tracker, "Dismiss", pendingIntent);

        notificationManager.notify(1, notificationBuilder.build());


    }
}
