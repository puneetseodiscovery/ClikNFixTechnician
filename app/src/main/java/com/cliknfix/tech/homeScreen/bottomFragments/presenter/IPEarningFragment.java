package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.responseModels.EarningsResponseModel;

public interface IPEarningFragment {
    void getEarnings(String token);
    void getEarningsSuccessResponse(EarningsResponseModel earningsResponseModel);
    void getEarningsFailureResponse(String message);
    void noJobDoneResponse(String msgg);
}
