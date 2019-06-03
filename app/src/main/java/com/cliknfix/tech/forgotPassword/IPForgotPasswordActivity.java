package com.cliknfix.tech.forgotPassword;

import com.cliknfix.tech.responseModels.ForgotPasswordResponseModel;

public interface IPForgotPasswordActivity {
    void resetPassword(String email);
    void onForgotPasswordSuccessResponse(ForgotPasswordResponseModel forgotPasswordResponseModel);
    void onForgotPasswordFailureResponse(String message);
}
