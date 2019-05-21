package com.cliknfix.tech.homeScreen.bottomFragments;

import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;

public interface IUserProfileFragment {

    void getUserProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel);
    void getUserProfileFailureFromPresenter(String message);
    void saveUserProfileSuccessFromPresenter(SaveUserProfileResponseModel model);
    void saveUserProfileFailureFromPresenter(String msgg);
}
