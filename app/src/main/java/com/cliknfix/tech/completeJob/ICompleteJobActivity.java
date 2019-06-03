package com.cliknfix.tech.completeJob;

import com.cliknfix.tech.responseModels.CompleteJobResponseModel;

public interface ICompleteJobActivity {
    void completeJobSuccessResponse(CompleteJobResponseModel completeJobResponseModel);
    void completeJobFailureResponse(String message);
}
