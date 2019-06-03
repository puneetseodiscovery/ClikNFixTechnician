package com.cliknfix.tech.otp;

import android.os.Message;

import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MOTPFragment implements IMOTPFragment {

    IPOTPFragment ipotpFragment;

    public MOTPFragment(POTPFragment potpFragment) {
        ipotpFragment = potpFragment;
    }

    @Override
    public void submitOTP(String otp, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitOTP(otp,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.FILL_OTP_SUCCESS:
                    SubmitOTPResponseModel submitOTPResponseModel = (SubmitOTPResponseModel) msg.obj;
                    ipotpFragment.onSubmitOTPSuccess(submitOTPResponseModel);
                    break;
                case APIInterface.FILL_OTP_FAILED:
                    String msgg = (String) msg.obj;
                    ipotpFragment.onSubmitOTPFailed(msgg);
                    break;
            }
        }
    };
}
