package com.cliknfix.tech.privacyPolicy;

import android.os.Message;

import com.cliknfix.tech.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MPrivacyPolicyActivity implements IMPrivacyPolicyActivity {

    IPPrivacyPolicyActivity ipPrivacyPolicyActivity;
    String value;

    public MPrivacyPolicyActivity(PPrivacyPolicyActivity pPrivacyPolicyActivity) {
        ipPrivacyPolicyActivity = pPrivacyPolicyActivity;
    }

    @Override
    public void privacyPolicy(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.privacyPolicy(token,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.PRIVACY_POLICY_SUCCESSS:
                    PrivacyPolicyResponseModel privacyPolicyResponseModel = (PrivacyPolicyResponseModel) msg.obj;
                    ipPrivacyPolicyActivity.onPrivacyPolicySuccessResponse(privacyPolicyResponseModel);
                    break;
                case APIInterface.PRIVACY_POLICY_FAILURE:
                    String message = (String) msg.obj;
                    ipPrivacyPolicyActivity.onPrivacyPolicyFailureResponse(message);
                    break;
            }
        }
    };
}
