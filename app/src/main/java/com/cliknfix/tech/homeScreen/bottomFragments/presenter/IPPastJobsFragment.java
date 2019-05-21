package com.cliknfix.tech.homeScreen.bottomFragments.presenter;

import com.cliknfix.tech.responseModels.PastJobsResponseModel;

public interface IPPastJobsFragment {
    void getPastJobsList(String token);
    void getPastJobsListSuccessResponse(PastJobsResponseModel pastJobsResponseModel);
    void getPastJobsListFailureResponse(String message);
    void noDataPastJobsListResponse(String msgg);
}
