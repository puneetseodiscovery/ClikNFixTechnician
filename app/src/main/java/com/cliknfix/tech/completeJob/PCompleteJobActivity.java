package com.cliknfix.tech.completeJob;

import com.cliknfix.tech.responseModels.CompleteJobResponseModel;

public class PCompleteJobActivity implements IPCompleteJobActivity {

    private ICompleteJobActivity iCompleteJobActivity;
    private IMCompleteJobActivity imCompleteJobActivity;


    public PCompleteJobActivity(CompleteJobActivity completeJobActivity) {
        iCompleteJobActivity = completeJobActivity;
        imCompleteJobActivity = new MCompleteJobActivity(this);
    }

    @Override
    public void completeJob(String token) {
        imCompleteJobActivity.completeJob(token);
    }

    @Override
    public void completeJobSuccessResponse(CompleteJobResponseModel completeJobResponseModel) {
        iCompleteJobActivity.completeJobSuccessResponse(completeJobResponseModel);
    }

    @Override
    public void completeJobFailureResponse(String message) {
        iCompleteJobActivity.completeJobFailureResponse(message);
    }
}
