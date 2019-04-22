package com.cliknfix.technician.chat;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.technician.R;
import com.cliknfix.technician.customerProfile.UpcomingCustomerProfileFragment;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void onBackClicked(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        UpcomingCustomerProfileFragment fragment = new UpcomingCustomerProfileFragment();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
