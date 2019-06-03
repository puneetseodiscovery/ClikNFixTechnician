package com.cliknfix.tech.homeScreen;

import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;

public class PHomeScreenActivity implements IPHomeScreenActivity {

    IHomeScreenActivity iHomeScreenActivity;
    IMHomeScreenActivity imHomeScreenActivity;

    public PHomeScreenActivity(HomeScreenActivity homeScreenActivity) {
        this.iHomeScreenActivity = homeScreenActivity;
        this.imHomeScreenActivity = new MHomeScreenActivity(this);
    }

    @Override
    public void getCustomerProfile(int id, String token) {
        imHomeScreenActivity.getCustomerProfile(id,token);
    }

    @Override
    public void getCustomerProfileSuccess(CustomerProfileResponseModel customerProfileResponseModel) {
        iHomeScreenActivity.getCustomerProfileSuccessFromPresenter(customerProfileResponseModel);
    }

    @Override
    public void getCustomerProfileFailure(String message) {
        iHomeScreenActivity.getCustomerProfileFailureFromPresenter(message);

    }

}
