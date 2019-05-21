package com.cliknfix.tech.acceptRejectJob;

import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;

public interface IAcceptRejectJobFragment {
    void acceptRejectJobSuccessFromPresenter(AcceptRejectResponseModel acceptRejectResponseModel);
    void acceptRejectJobFailureFromPresenter(String message);
}
