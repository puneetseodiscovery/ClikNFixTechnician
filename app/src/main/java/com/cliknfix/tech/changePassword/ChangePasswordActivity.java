package com.cliknfix.tech.changePassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;

public class ChangePasswordActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }
}
