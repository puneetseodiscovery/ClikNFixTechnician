package com.cliknfix.tech.login;

import android.os.Message;

import com.cliknfix.tech.responseModels.LoginResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MLoginActivity implements IMLoginActivity {

    IPLoginActivity ipLoginActivity;

    public MLoginActivity(PLoginActivity pLoginActivity) {
        ipLoginActivity = pLoginActivity;
    }

    @Override
    public void doLogin(String email, String password, double currentLatitude, double currentLongitude, String deviceId) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.loginUser(email,password,currentLatitude,currentLongitude,deviceId,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = (LoginResponseModel) msg.obj;
                    ipLoginActivity.onLoginSuccess(loginResponseModel);
                    break;
                case APIInterface.LOGIN_FAILED:
                    String msgg = (String) msg.obj;
                    ipLoginActivity.onLoginFailed(msgg);
                    break;
            }
        }
    };
}
