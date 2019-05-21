package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.homeScreen.bottomFragments.IUserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.IMUserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.MUserProfileFragment;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;

public class PUserProfileFragment implements IPUserProfileFragment {
    IUserProfileFragment iUserProfileFragment;
    IMUserProfileFragment imUserProfileFragment;

    public PUserProfileFragment(UserProfileFragment userProfileFragment) {
        this.iUserProfileFragment = userProfileFragment;
        this.imUserProfileFragment = new MUserProfileFragment(this);
    }

    @Override
    public void getUserProfile(int id, String token) {
        imUserProfileFragment.getUserProfile(id,token);
    }

    @Override
    public void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel) {
        iUserProfileFragment.getUserProfileSuccessFromPresenter(userProfileResponseModel);
    }

    @Override
    public void getUserProfileFailure(String message) {
        iUserProfileFragment.getUserProfileFailureFromPresenter(message);
    }

    @Override
    public void saveUserProfile(String name, String age, String address, String token) {
        imUserProfileFragment.saveUserProfile(name,age,address,token);
    }

    @Override
    public void saveUserProfileSuccess(SaveUserProfileResponseModel model) {
        iUserProfileFragment.saveUserProfileSuccessFromPresenter(model);
    }

    @Override
    public void saveUserProfileFailure(String msgg) {
        iUserProfileFragment.saveUserProfileFailureFromPresenter(msgg);
    }
}
