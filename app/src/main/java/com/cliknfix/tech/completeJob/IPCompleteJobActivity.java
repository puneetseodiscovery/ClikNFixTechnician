package com.cliknfix.tech.completeJob;

import com.cliknfix.tech.responseModels.CompleteJobResponseModel;

public interface IPCompleteJobActivity {
    void completeJob(String token);
    void completeJobSuccessResponse(CompleteJobResponseModel completeJobResponseModel);
    void completeJobFailureResponse(String message);
}
