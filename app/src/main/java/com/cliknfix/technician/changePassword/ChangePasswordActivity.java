package com.cliknfix.technician.changePassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;

public class ChangePasswordActivity extends AppCompatActivity {

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
