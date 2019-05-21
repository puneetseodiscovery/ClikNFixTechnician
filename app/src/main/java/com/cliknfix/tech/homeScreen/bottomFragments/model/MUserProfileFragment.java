package com.cliknfix.tech.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MUserProfileFragment implements IMUserProfileFragment {

    IPUserProfileFragment ipUserProfileFragment;

    public MUserProfileFragment(PUserProfileFragment pUserProfileFragment) {
        this.ipUserProfileFragment = pUserProfileFragment;
    }

    @Override
    public void getUserProfile(int id, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getUserProfile(id,token,mHandler);
    }

    @Override
    public void saveUserProfile(String name, String age, String address, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.saveUserProfile(name,age,address,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.USER_PROFILE_SUCCESS:
                    UserProfileResponseModel userProfileResponseModel = (UserProfileResponseModel) msg.obj;
                    ipUserProfileFragment.getUserProfileSuccess(userProfileResponseModel);
                    break;

                case APIInterface.USER_PROFILE_FAILED:
                    String message = (String) msg.obj;
                    ipUserProfileFragment.getUserProfileFailure(message);
                    break;
                case APIInterface.SAVE_USER_PROFILE_SUCCESS:
                    SaveUserProfileResponseModel model = (SaveUserProfileResponseModel) msg.obj;
                    ipUserProfileFragment.saveUserProfileSuccess(model);
                    break;

                case APIInterface.SAVE_USER_PROFILE_FAILURE:
                    String msgg = (String) msg.obj;
                    ipUserProfileFragment.saveUserProfileFailure(msgg);
                    break;
            }
        }
    };
}
