package com.cliknfix.tech.changePassword;

import com.cliknfix.tech.responseModels.ChangePasswordResponseModel;

public interface IChangePasswordActivity {
    void onChangePasswordSuccessResponseFromPresenter(ChangePasswordResponseModel changePasswordResponseModel);
    void onChangePasswordFailureResponseFromPresenter(String message);
}
