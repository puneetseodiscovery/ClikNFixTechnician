package com.cliknfix.tech.congratulations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;

public class CongratulatonsActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulatons);
    }

    public void onDoneClicked(View view) {
        startActivity(new Intent(this, HomeScreenActivity.class));
    }
}
