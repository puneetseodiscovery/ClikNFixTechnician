package com.cliknfix.tech.acceptRejectJob;

import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;

public interface IPAcceptRejectJobFragment {
    void acceptRejectJob(String userId, String message, String i, String token);
    void acceptRejectJobSuccess(AcceptRejectResponseModel acceptRejectResponseModel);
    void acceptRejectJobFailure(String message);
}
