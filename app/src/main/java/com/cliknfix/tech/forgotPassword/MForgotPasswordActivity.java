package com.cliknfix.tech.forgotPassword;

import android.os.Message;

import com.cliknfix.tech.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MForgotPasswordActivity implements IMForgotPasswordActivity {

    IPForgotPasswordActivity ipForgotPasswordActivity;
    String value;

    public MForgotPasswordActivity(PForgotPasswordActivity pForgotPasswordActivity) {
        ipForgotPasswordActivity = pForgotPasswordActivity;
    }

    @Override
    public void forgotPasswordRestCall(String phoneNumber) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.forgotPassword(phoneNumber,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.FORGOT_SUCCESS:
                    ForgotPasswordResponseModel forgotPasswordResponseModel = (ForgotPasswordResponseModel) msg.obj;
                    ipForgotPasswordActivity.onForgotPasswordSuccessResponse(forgotPasswordResponseModel);

                    break;
                case APIInterface.FORGOT_FAILED:
                    String message = (String) msg.obj;
                    ipForgotPasswordActivity.onForgotPasswordFailureResponse(message);
                    break;
            }
        }
    };

}
