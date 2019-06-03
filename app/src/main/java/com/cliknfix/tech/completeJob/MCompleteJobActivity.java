package com.cliknfix.tech.completeJob;

import android.os.Message;

import com.cliknfix.tech.responseModels.CompleteJobResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MCompleteJobActivity implements IMCompleteJobActivity {

    IPCompleteJobActivity ipCompleteJobActivity;

    public MCompleteJobActivity(PCompleteJobActivity pCompleteJobActivity) {
        ipCompleteJobActivity = pCompleteJobActivity;
    }

    @Override
    public void completeJob(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.completeJob(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.COMPLETE_JOB_SUCCESS:
                    CompleteJobResponseModel completeJobResponseModel = (CompleteJobResponseModel) msg.obj;
                    ipCompleteJobActivity.completeJobSuccessResponse(completeJobResponseModel);
                    break;
                case APIInterface.COMPLETE_JOB_FAILED:
                    String message = (String) msg.obj;
                    ipCompleteJobActivity.completeJobFailureResponse(message);
                    break;
            }
        }
    };
}
