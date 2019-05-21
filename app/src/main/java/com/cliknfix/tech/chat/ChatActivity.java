package com.cliknfix.tech.chat;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.customerProfile.UpcomingCustomerProfileFragment;

public class ChatActivity extends BaseClass {

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
