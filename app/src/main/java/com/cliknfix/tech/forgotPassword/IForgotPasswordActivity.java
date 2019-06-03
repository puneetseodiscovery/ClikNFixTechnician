package com.cliknfix.tech.forgotPassword;

import com.cliknfix.tech.responseModels.ForgotPasswordResponseModel;

public interface IForgotPasswordActivity {
    void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void onForgotPasswordFailureResponseFromPresenter(String message);
}
