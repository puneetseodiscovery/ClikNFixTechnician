package com.cliknfix.tech.homeScreen.bottomFragments;

import com.cliknfix.tech.responseModels.LogoutResponseModel;

public interface ISettingsFragment {
    void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel);
    void logoutFailureFromPresenter(String message);
}
