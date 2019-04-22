package com.cliknfix.technician.payment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.congratulations.CongratulatonsActivity;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_successful_text)
    TextView tvSuccessfulText;
    @BindView(R.id.tv_approve_text)
    TextView tvApproveText;
    @BindView(R.id.tv_transaction_details_text)
    TextView tvTransactionDetailsText;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.btn_continue)
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvSuccessfulText.setTypeface(Utility.typeFaceForBoldText(this));
        tvApproveText.setTypeface(Utility.typeFaceForBoldText(this));
        tvTransactionDetailsText.setTypeface(Utility.typeFaceForText(this));
        tvMoney.setTypeface(Utility.typeFaceForBoldText(this));
        btnContinue.setTypeface(Utility.typeFaceForBoldText(this));
    }


    public void onContinueClicked(View view) {
        startActivity(new Intent(this, CongratulatonsActivity.class));
    }
}
