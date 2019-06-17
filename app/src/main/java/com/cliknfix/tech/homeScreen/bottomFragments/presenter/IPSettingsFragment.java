package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.responseModels.LogoutResponseModel;

public interface IPSettingsFragment {
    void doLogout(int userId);
    void logoutSuccess(LogoutResponseModel logoutResponseModel);
    void logoutFailure(String message);
}
