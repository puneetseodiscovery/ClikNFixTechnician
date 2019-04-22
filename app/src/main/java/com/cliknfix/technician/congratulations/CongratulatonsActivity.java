package com.cliknfix.technician.congratulations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;

public class CongratulatonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulatons);
    }

    public void onDoneClicked(View view) {
        startActivity(new Intent(this, HomeScreenActivity.class));
    }
}
