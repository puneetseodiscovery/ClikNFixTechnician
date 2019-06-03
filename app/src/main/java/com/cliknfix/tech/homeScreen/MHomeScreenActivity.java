package com.cliknfix.tech.homeScreen;

import android.os.Message;

import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MHomeScreenActivity implements IMHomeScreenActivity {

    IPHomeScreenActivity ipHomeScreenActivity;

    public MHomeScreenActivity(PHomeScreenActivity pHomeScreenActivity) {
        this.ipHomeScreenActivity = pHomeScreenActivity;
    }

    @Override
    public void getCustomerProfile(int id, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getCustomerProfile(id,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.CUSTOMER_PROFILE_SUCCESS:
                    CustomerProfileResponseModel customerProfileResponseModel = (CustomerProfileResponseModel) msg.obj;
                    ipHomeScreenActivity.getCustomerProfileSuccess(customerProfileResponseModel);
                    break;

                case APIInterface.CUSTOMER_PROFILE_FAILED:
                    String message = (String) msg.obj;
                    ipHomeScreenActivity.getCustomerProfileFailure(message);
                    break;
            }
        }
    };
}
