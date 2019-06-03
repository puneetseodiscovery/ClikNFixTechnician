package com.cliknfix.tech.homeScreen;

import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;

public interface IPHomeScreenActivity {
    void getCustomerProfile(int userId, String token);
    void getCustomerProfileSuccess(CustomerProfileResponseModel customerProfileResponseModel);
    void getCustomerProfileFailure(String message);
}
