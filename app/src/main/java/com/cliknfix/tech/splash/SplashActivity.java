package com.cliknfix.tech.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.login.LoginActivity;
import com.cliknfix.tech.util.PreferenceHandler;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class SplashActivity extends BaseClass {

    String session;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        session = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        //Toast.makeText(SplashActivity.this, "Session:"+session, Toast.LENGTH_SHORT).show();
                        if(session.length()>0){
                            Intent mainIntent = new Intent(SplashActivity.this, HomeScreenActivity.class);
                            SplashActivity.this.startActivity(mainIntent);
                            SplashActivity.this.finish();
                        } else {
                            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                            SplashActivity.this.startActivity(mainIntent);
                            SplashActivity.this.finish();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);
                finishAffinity();
            }
        });
    }

}
