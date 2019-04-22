package com.cliknfix.technician.completeJob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.technician.R;
import com.cliknfix.technician.payment.PaymentSuccessActivity;

public class CompleteJobActivity extends AppCompatActivity {

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
