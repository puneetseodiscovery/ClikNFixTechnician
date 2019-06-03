package com.cliknfix.tech.homeScreen;

import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;

public interface IHomeScreenActivity {
    void getCustomerProfileSuccessFromPresenter(CustomerProfileResponseModel customerProfileResponseModel);
    void getCustomerProfileFailureFromPresenter(String message);
}
