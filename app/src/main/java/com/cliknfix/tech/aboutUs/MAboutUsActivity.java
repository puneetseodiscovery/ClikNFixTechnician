package com.cliknfix.tech.aboutUs;

import android.os.Message;

import com.cliknfix.tech.responseModels.AboutUsResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MAboutUsActivity implements IMAboutUsActivity {

    IPAboutUsActivity ipAboutUsActivity;

    public MAboutUsActivity(PAboutUsActivity pAboutUsActivity) {
        ipAboutUsActivity = pAboutUsActivity;
    }

    @Override
    public void aboutUs(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.aboutUs(token,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.ABOUT_US_SUCCESS:
                    AboutUsResponseModel aboutUsResponseModel = (AboutUsResponseModel) msg.obj;
                    ipAboutUsActivity.onAboutUsSuccessResponse(aboutUsResponseModel);
                    break;
                case APIInterface.ABOUT_US_FAILURE:
                    String message = (String) msg.obj;
                    ipAboutUsActivity.onAboutUsFailureResponse(message);
                    break;
            }
        }
    };
}
