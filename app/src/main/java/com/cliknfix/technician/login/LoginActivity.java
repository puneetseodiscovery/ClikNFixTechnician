package com.cliknfix.technician.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.forgotPassword.ForgotPasswordActivity;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_text)
    TextView tvLoginText;
    @BindView(R.id.et_email_login)
    EditText etEmail;
    @BindView(R.id.et_password_login)
    EditText etPassword;
    @BindView(R.id.iv_password)
    ImageView ivPassword;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.btn_signin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etEmail.setTypeface(Utility.typeFaceForText(this));
        /*etPassword.setTypeface(Utility.typeFaceForText(this));
        tvLoginText.setTypeface(Utility.typeFaceForBoldText(this));
        btnLogin.setTypeface(Utility.typeFaceForBoldText(this));*/
    }

    public void onLoginClicked(View view) {
        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    public void onForgotPasswordClicked(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}
