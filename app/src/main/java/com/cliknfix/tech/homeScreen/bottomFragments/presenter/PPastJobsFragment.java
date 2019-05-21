package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import android.util.Log;

import com.cliknfix.tech.homeScreen.bottomFragments.IPastJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.PastJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.IMPastJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.MPastJobsFragment;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;

public class PPastJobsFragment implements IPPastJobsFragment {

    IPastJobsFragment iPastJobsFragment;
    IMPastJobsFragment imPastJobsFragment;

    public PPastJobsFragment(PastJobsFragment pastJobsFragment) {
        this.iPastJobsFragment = pastJobsFragment;
        this.imPastJobsFragment = new MPastJobsFragment(this);
    }

    @Override
    public void getPastJobsList(String token) {
        imPastJobsFragment.getPastJobsList(token);
    }

    @Override
    public void getPastJobsListSuccessResponse(PastJobsResponseModel pastJobsResponseModel) {
        iPastJobsFragment.getPastJobsListSuccessFromPresenter(pastJobsResponseModel);
    }

    @Override
    public void getPastJobsListFailureResponse(String message) {
        iPastJobsFragment.getPastJobsListFailureFromPresenter(message);
    }

    @Override
    public void noDataPastJobsListResponse(String msgg) {
        Log.e("noDataPastJobsResponse","Working");
        iPastJobsFragment.noDataPastJobsListResponse(msgg);
    }
}
