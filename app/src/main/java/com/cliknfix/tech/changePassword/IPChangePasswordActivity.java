package com.cliknfix.tech.changePassword;

import com.cliknfix.tech.responseModels.ChangePasswordResponseModel;

public interface IPChangePasswordActivity {
    void changePassword(String oldPass, String newPass, String confirmPass, String token);
    void onChangePasswordSuccessResponse(ChangePasswordResponseModel changePasswordResponseModel);
    void onChangePasswordFailureResponse(String message);
}
