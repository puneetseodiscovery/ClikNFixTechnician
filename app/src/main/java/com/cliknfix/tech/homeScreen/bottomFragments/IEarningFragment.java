package com.cliknfix.tech.homeScreen.bottomFragments;

import com.cliknfix.tech.responseModels.EarningsResponseModel;

public interface IEarningFragment {
    void getEarningsSuccessFromPresenter(EarningsResponseModel earningsResponseModel);
    void getEarningsFailureFromPresenter(String message);
}
