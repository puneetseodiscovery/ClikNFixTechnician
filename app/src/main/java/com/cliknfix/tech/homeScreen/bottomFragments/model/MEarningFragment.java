package com.cliknfix.tech.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPEarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PEarningFragment;
import com.cliknfix.tech.responseModels.EarningsResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MEarningFragment implements IMEarningFragment {

    IPEarningFragment ipEarningFragment;

    public MEarningFragment(PEarningFragment pEarningFragment) {
        this.ipEarningFragment = pEarningFragment;
    }

    @Override
    public void getEarnings(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getEarnings(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.GET_EARNINGS_SUCCESS:
                    EarningsResponseModel earningsResponseModel = (EarningsResponseModel) msg.obj;
                    ipEarningFragment.getEarningsSuccessResponse(earningsResponseModel);
                    break;
                case APIInterface.GET_EARNINGS_FAILED:
                    String message = (String) msg.obj;
                    ipEarningFragment.getEarningsFailureResponse(message);
                    break;
            }
        }
    };
}
