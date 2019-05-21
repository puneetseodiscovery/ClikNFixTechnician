package com.cliknfix.tech.homeScreen.bottomFragments;

import com.cliknfix.tech.responseModels.PastJobsResponseModel;

public interface IPastJobsFragment {
    void getPastJobsListSuccessFromPresenter(PastJobsResponseModel pastJobsResponseModel);
    void getPastJobsListFailureFromPresenter(String message);
    void noDataPastJobsListResponse(String msgg);
}
