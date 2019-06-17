package com.cliknfix.tech.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPSettingsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PSettingsFragment;
import com.cliknfix.tech.responseModels.LogoutResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MSettingsFragment implements IMSettingsFragment {

    IPSettingsFragment ipSettingsFragment;

    public MSettingsFragment(PSettingsFragment pSettingsFragment) {
        this.ipSettingsFragment = pSettingsFragment;
    }

    @Override
    public void doLogout(int user_id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.doLogout(user_id,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = (LogoutResponseModel) msg.obj;
                    ipSettingsFragment.logoutSuccess(logoutResponseModel);
                    break;

                case APIInterface.LOGOUT_FAILED:
                    String message = (String) msg.obj;
                    ipSettingsFragment.logoutFailure(message);
                    break;
            }
        }
    };

}
