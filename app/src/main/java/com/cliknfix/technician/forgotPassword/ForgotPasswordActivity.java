package com.cliknfix.technician.forgotPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.login.LoginActivity;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_forgot_pass)
    TextView tvForgotPass;
    @BindView(R.id.tv_ptext)
    TextView tvP1;
    @BindView(R.id.et_email_forgot_pass)
    EditText etEmail;
    @BindView(R.id.btn_reset_pass)
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        init();
    }

    public  void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvForgotPass.setTypeface(Utility.typeFaceForBoldText(this));
        etEmail.setTypeface(Utility.typeFaceForText(this));
        tvP1.setTypeface(Utility.typeFaceForText(this));
        btnReset.setTypeface(Utility.typeFaceForBoldText(this));
    }

    public void onBackClicked(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onResetPasswordClicked(View view) {

    }
}
