package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;

public interface IPUserProfileFragment {

    void getUserProfile(int id, String token);
    void getUserProfileSuccess(UserProfileResponseModel userProfileResponseModel);
    void getUserProfileFailure(String message);
    void saveUserProfile(String name, String age, String address, String token);
    void saveUserProfileSuccess(SaveUserProfileResponseModel model);
    void saveUserProfileFailure(String msgg);


}
