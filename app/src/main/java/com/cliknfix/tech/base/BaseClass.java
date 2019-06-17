package com.cliknfix.tech.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cliknfix.tech.util.PreferenceHandler;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseClass extends AppCompatActivity{

    public static String deviceToken;

    public static String firebaseUsername = "";
    public static String firebasePassword = "";
    public static String firebaseChatWith = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        //Newer version of Firebase
        /*if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/

        getDeviceToken();
    }

    public void getDeviceToken() {
        startService(new Intent(this, BaseFirebaseMessagingService.class));
        deviceToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_FIREBASE_TOKEN, "");
        //Toast.makeText(this, "Device Token:" + deviceToken, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
