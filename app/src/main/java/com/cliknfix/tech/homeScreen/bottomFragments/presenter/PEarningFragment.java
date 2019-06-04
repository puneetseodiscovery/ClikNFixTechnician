package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.homeScreen.bottomFragments.EarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.IEarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.IMEarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.MEarningFragment;
import com.cliknfix.tech.responseModels.EarningsResponseModel;

public class PEarningFragment implements IPEarningFragment {

    IEarningFragment iEarningFragment;
    IMEarningFragment imEarningFragment;

    public PEarningFragment(EarningFragment earningFragment) {
        this.iEarningFragment = earningFragment;
        this.imEarningFragment = new MEarningFragment(this);
    }

    @Override
    public void getEarnings(String token) {
        imEarningFragment.getEarnings(token);
    }

    @Override
    public void getEarningsSuccessResponse(EarningsResponseModel earningsResponseModel) {
        iEarningFragment.getEarningsSuccessFromPresenter(earningsResponseModel);
    }

    @Override
    public void getEarningsFailureResponse(String message) {
        iEarningFragment.getEarningsFailureFromPresenter(message);
    }

    @Override
    public void noJobDoneResponse(String msgg) {
        iEarningFragment.noJobDoneFromPresenter(msgg);
    }
}
