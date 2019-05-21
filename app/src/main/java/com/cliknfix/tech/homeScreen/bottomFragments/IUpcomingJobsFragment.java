package com.cliknfix.tech.homeScreen.bottomFragments;

import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;

public interface IUpcomingJobsFragment {
    void getUpcomingJobsListSuccessFromPresenter(UpcomingJobsResponseModel upcomingJobsResponseModel);
    void getUpcomingJobsListFailureFromPresenter(String message);
    void noDataUpcomingJobsListResponse(String msgg);
}
