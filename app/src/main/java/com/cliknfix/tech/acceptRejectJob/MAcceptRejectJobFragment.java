package com.cliknfix.tech.acceptRejectJob;

import android.os.Message;

import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MAcceptRejectJobFragment implements IMAcceptRejectJobFragment {

    IPAcceptRejectJobFragment ipAcceptRejectJobFragment;

    public MAcceptRejectJobFragment(PAcceptRejectJobFragment pAcceptRejectJobFragment) {
        this.ipAcceptRejectJobFragment = pAcceptRejectJobFragment;
    }

    @Override
    public void acceptRejectJob(String userId, String message, String i, String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.acceptRejectJob(userId,message,i,token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.ACCEPT_REJECT_JOB_SUCCESS:
                    AcceptRejectResponseModel acceptRejectResponseModel = (AcceptRejectResponseModel) msg.obj;
                    ipAcceptRejectJobFragment.acceptRejectJobSuccess(acceptRejectResponseModel);
                    break;

                case APIInterface.ACCEPT_REJECT_JOB_FAILURE:
                    String message = (String) msg.obj;
                    ipAcceptRejectJobFragment.acceptRejectJobFailure(message);
                    break;
            }
        }
    };
}
