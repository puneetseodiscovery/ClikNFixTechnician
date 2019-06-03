package com.cliknfix.tech.otp;

import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;

public interface IPOTPFragment {
    void submitOTP(String otp, String token);
    void onSubmitOTPSuccess(SubmitOTPResponseModel submitOTPResponseModel);
    void onSubmitOTPFailed(String msgg);
}
