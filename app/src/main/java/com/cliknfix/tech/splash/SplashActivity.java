package com.cliknfix.tech.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.login.LoginActivity;
import com.cliknfix.tech.util.PreferenceHandler;

public class SplashActivity extends BaseClass {

    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                /*Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();*/
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

}
