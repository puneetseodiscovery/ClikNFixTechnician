package com.cliknfix.tech.homeScreen.bottomFragments.model;

import android.os.Message;
import android.util.Log;

import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPPastJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PPastJobsFragment;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MPastJobsFragment implements IMPastJobsFragment {

    IPPastJobsFragment ipPastJobsFragment;

    public MPastJobsFragment(PPastJobsFragment pPastJobsFragment) {
        this.ipPastJobsFragment = pPastJobsFragment;
    }

    @Override
    public void getPastJobsList(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getPastJobsList(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.GETPASTJOBSLIST_SUCCESS:
                    PastJobsResponseModel pastJobsResponseModel = (PastJobsResponseModel) msg.obj;
                    ipPastJobsFragment.getPastJobsListSuccessResponse(pastJobsResponseModel);
                    break;
                case APIInterface.GETPASTJOBSLIST_NO_DATA:
                    Log.e("GETPASTJOBSLIST_NO_DATA","Working");
                    String msgg = (String) msg.obj;
                    ipPastJobsFragment.noDataPastJobsListResponse(msgg);
                    break;
                case APIInterface.GETPASTJOBSLIST_FAILED:
                    String message = (String) msg.obj;
                    ipPastJobsFragment.getPastJobsListFailureResponse(message);
                    break;
            }
        }
    };
}
