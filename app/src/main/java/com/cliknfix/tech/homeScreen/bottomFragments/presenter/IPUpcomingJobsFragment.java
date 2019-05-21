package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;

public interface IPUpcomingJobsFragment {
    void getUpcomingJobsList(String token);
    void getUpcomingJobsListFailureResponse(String message);
    void getUpcomingJobsListSuccessResponse(UpcomingJobsResponseModel upcomingJobsResponseModel);
    void noDataUpcomingJobsListResponse(String msgg);
}
