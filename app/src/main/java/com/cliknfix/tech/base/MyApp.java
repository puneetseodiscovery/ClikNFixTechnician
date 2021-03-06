package com.cliknfix.tech.base;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.cliknfix.tech.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {

    private static MyApp sAppContext;

    public static synchronized MyApp getInstance() {
        return sAppContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/bahnschrift_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
