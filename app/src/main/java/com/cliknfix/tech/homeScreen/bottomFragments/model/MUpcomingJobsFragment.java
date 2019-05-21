package com.cliknfix.tech.homeScreen.bottomFragments.model;

import android.os.Message;

import com.cliknfix.tech.homeScreen.bottomFragments.UpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PUpcomingJobsFragment;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MUpcomingJobsFragment implements IMUpcomingJobsFragment {

    IPUpcomingJobsFragment ipUpcomingJobsFragment;

    public MUpcomingJobsFragment(PUpcomingJobsFragment pUpcomingJobsFragment) {
        this.ipUpcomingJobsFragment = pUpcomingJobsFragment;
    }

    @Override
    public void getUpcomingJobsList(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getUpcomingJobsList(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.GETUPCOMINGJOBSLIST_SUCCESS:
                    UpcomingJobsResponseModel upcomingJobsResponseModel = (UpcomingJobsResponseModel) msg.obj;
                    ipUpcomingJobsFragment.getUpcomingJobsListSuccessResponse(upcomingJobsResponseModel);
                    break;
                case APIInterface.GETUPCOMINGJOBSLIST_NO_DATA:
                    String msgg = (String) msg.obj;
                    ipUpcomingJobsFragment.noDataUpcomingJobsListResponse(msgg);
                    break;
                case APIInterface.GETUPCOMINGJOBSLIST_FAILED:
                    String message = (String) msg.obj;
                    ipUpcomingJobsFragment.getUpcomingJobsListFailureResponse(message);
                    break;
            }
        }
    };
}
