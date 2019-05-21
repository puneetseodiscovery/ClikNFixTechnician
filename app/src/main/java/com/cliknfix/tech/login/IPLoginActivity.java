package com.cliknfix.tech.login;

import com.cliknfix.tech.responseModels.LoginResponseModel;

public interface IPLoginActivity {
    void doLogin(String email, String password, double currentLatitude, double currentLongitude, String deviceId);
    void onLoginSuccess(LoginResponseModel loginResponseModel);
    void onLoginFailed(String msgg);
}
