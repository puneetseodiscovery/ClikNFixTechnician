package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.homeScreen.bottomFragments.IUpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.UpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.IMUpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.MUpcomingJobsFragment;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;

public class PUpcomingJobsFragment implements IPUpcomingJobsFragment {

    IUpcomingJobsFragment iUpcomingJobsFragment;
    IMUpcomingJobsFragment imUpcomingJobsFragment;

    public PUpcomingJobsFragment(UpcomingJobsFragment upcomingJobsFragment) {
        this.iUpcomingJobsFragment = upcomingJobsFragment;
        this.imUpcomingJobsFragment = new MUpcomingJobsFragment(this);
    }


    @Override
    public void getUpcomingJobsList(String token) {
        imUpcomingJobsFragment.getUpcomingJobsList(token);
    }

    @Override
    public void getUpcomingJobsListFailureResponse(String message) {
        iUpcomingJobsFragment.getUpcomingJobsListFailureFromPresenter(message);

    }

    @Override
    public void getUpcomingJobsListSuccessResponse(UpcomingJobsResponseModel upcomingJobsResponseModel) {
        iUpcomingJobsFragment.getUpcomingJobsListSuccessFromPresenter(upcomingJobsResponseModel);
    }

    @Override
    public void noDataUpcomingJobsListResponse(String msgg) {
        iUpcomingJobsFragment.noDataUpcomingJobsListResponse(msgg);
    }
}
