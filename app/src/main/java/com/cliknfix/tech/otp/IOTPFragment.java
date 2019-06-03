package com.cliknfix.tech.otp;

import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;

public interface IOTPFragment {
    void onSubmitOTPSuccessFromPresenter(SubmitOTPResponseModel submitOTPResponseModel);
    void onSubmitOTPFailedFromPresenter(String msgg);
}
