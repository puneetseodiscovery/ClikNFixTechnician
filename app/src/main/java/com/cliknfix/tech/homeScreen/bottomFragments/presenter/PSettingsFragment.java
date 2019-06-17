package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.homeScreen.bottomFragments.ISettingsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.SettingsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.IMSettingsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.MSettingsFragment;
import com.cliknfix.tech.responseModels.LogoutResponseModel;

public class PSettingsFragment implements IPSettingsFragment {

    ISettingsFragment iSettingsFragment;
    IMSettingsFragment imSettingsFragment;

    public PSettingsFragment(SettingsFragment settingsFragment) {
        this.iSettingsFragment = settingsFragment;
        this.imSettingsFragment = new MSettingsFragment(this);
    }

    @Override
    public void doLogout(int user_id) {
        imSettingsFragment.doLogout(user_id);
    }

    @Override
    public void logoutSuccess(LogoutResponseModel logoutResponseModel) {
        iSettingsFragment.logoutSuccessFromPresenter(logoutResponseModel);
    }

    @Override
    public void logoutFailure(String message) {
        iSettingsFragment.logoutFailureFromPresenter(message);
    }

}
