package com.cliknfix.tech.completeJob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.payment.PaymentSuccessActivity;
import com.cliknfix.tech.responseModels.CompleteJobResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;

public class CompleteJobActivity extends BaseClass implements ICompleteJobActivity {

    ProgressDialog progressDialog;
    IPCompleteJobActivity ipCompleteJobActivity;
    String labourRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_job);
        ipCompleteJobActivity = new PCompleteJobActivity(this);
        labourRate = getIntent().getStringExtra("labour_rate");

    }

    public void onSwipeClicked(View view) {
        boolean b = new PreferenceHandler().readStartJobBoolean(MyApp.getInstance().getApplicationContext(), PreferenceHandler.START_JOB_LABOUR, false);
        Log.e("Complete OTP Filled","" + b);
        progressDialog = Utility.showLoader(this);
        ipCompleteJobActivity.completeJob(Utility.getToken());
        /*Intent intent = new Intent(this, PaymentSuccessActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void completeJobSuccessResponse(CompleteJobResponseModel completeJobResponseModel) {
        progressDialog.dismiss();
        new PreferenceHandler().writeStartJobBoolean(MyApp.getInstance().getApplicationContext(), PreferenceHandler.START_JOB_OTP_FILL, false);
        Intent intent = new Intent(this, PaymentSuccessActivity.class);
        intent.putExtra("labour_rate",labourRate);
        startActivity(intent);
    }

    @Override
    public void completeJobFailureResponse(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+ message, Toast.LENGTH_SHORT).show();
    }
}
