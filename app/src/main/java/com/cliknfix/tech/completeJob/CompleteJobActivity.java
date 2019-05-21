package com.cliknfix.tech.completeJob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.payment.PaymentSuccessActivity;

public class CompleteJobActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_job);
    }

    public void onSwipeClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, PaymentSuccessActivity.class);
        startActivity(intent);
    }
}
