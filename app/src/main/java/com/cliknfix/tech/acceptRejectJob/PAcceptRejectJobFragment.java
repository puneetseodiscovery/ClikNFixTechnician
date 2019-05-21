package com.cliknfix.tech.acceptRejectJob;

import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;

public class PAcceptRejectJobFragment implements IPAcceptRejectJobFragment {

    IAcceptRejectJobFragment iAcceptRejectJobFragment;
    IMAcceptRejectJobFragment imAcceptRejectJobFragment;

    public PAcceptRejectJobFragment(AcceptRejectJobFragment acceptRejectJobFragment) {
        this.iAcceptRejectJobFragment = acceptRejectJobFragment;
        this.imAcceptRejectJobFragment = new MAcceptRejectJobFragment(this);
    }

    @Override
    public void acceptRejectJob(String userId, String message, String i, String token) {
        imAcceptRejectJobFragment.acceptRejectJob(userId,message,i,token);
    }

    @Override
    public void acceptRejectJobSuccess(AcceptRejectResponseModel acceptRejectResponseModel) {
        iAcceptRejectJobFragment.acceptRejectJobSuccessFromPresenter(acceptRejectResponseModel);
    }

    @Override
    public void acceptRejectJobFailure(String message) {
        iAcceptRejectJobFragment.acceptRejectJobFailureFromPresenter(message);
    }
}
