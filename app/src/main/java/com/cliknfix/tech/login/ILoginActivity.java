package com.cliknfix.tech.login;

import com.cliknfix.tech.responseModels.LoginResponseModel;

public interface ILoginActivity {
    void onLoginSuccessFromPresenter(LoginResponseModel userModelLoginResponse);
    void onLoginFailedFromPresenter(String message);
}
