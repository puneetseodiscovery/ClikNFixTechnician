package com.cliknfix.tech.otp;

import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;

public class POTPFragment implements IPOTPFragment {

    private IOTPFragment iotpFragment;
    private IMOTPFragment imotpFragment;


    public POTPFragment(OTPFragment otpFragment) {
        iotpFragment = otpFragment;
        imotpFragment = new MOTPFragment(this);
    }

    @Override
    public void submitOTP(String otp, String token) {
        imotpFragment.submitOTP(otp,token);
    }

    @Override
    public void onSubmitOTPSuccess(SubmitOTPResponseModel submitOTPResponseModel) {
        iotpFragment.onSubmitOTPSuccessFromPresenter(submitOTPResponseModel);
    }

    @Override
    public void onSubmitOTPFailed(String msgg) {
        iotpFragment.onSubmitOTPFailedFromPresenter(msgg);
    }
}
