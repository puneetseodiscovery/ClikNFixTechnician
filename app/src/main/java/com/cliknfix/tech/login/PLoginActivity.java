package com.cliknfix.tech.login;

import com.cliknfix.tech.responseModels.LoginResponseModel;

public class PLoginActivity implements IPLoginActivity {

    private ILoginActivity iLoginActivity;
    private IMLoginActivity imLoginActivity;


    public PLoginActivity(LoginActivity loginActivity) {
        iLoginActivity = loginActivity;
        imLoginActivity = new MLoginActivity(this);
    }

    @Override
    public void doLogin(String email, String password, double currentLatitude, double currentLongitude, String deviceId) {
        imLoginActivity.doLogin(email,password,currentLatitude,currentLongitude,deviceId);
    }

    @Override
    public void onLoginSuccess(LoginResponseModel userModelLoginResponse) {
        iLoginActivity.onLoginSuccessFromPresenter(userModelLoginResponse);
    }

    @Override
    public void onLoginFailed(String message) {
        iLoginActivity.onLoginFailedFromPresenter(message);
    }
}
