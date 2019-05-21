package com.cliknfix.tech.ratingsReview.model;

import android.os.Message;

import com.cliknfix.tech.ratingsReview.presenter.IPRatingActivity;
import com.cliknfix.tech.ratingsReview.presenter.PRatingActivity;
import com.cliknfix.tech.responseModels.ReviewsResponseModel;
import com.cliknfix.tech.retrofit.APIInterface;
import com.cliknfix.tech.retrofit.RetrofitCalls;

public class MRatingActivity implements IMRatingActivity {

    IPRatingActivity ipRatingActivity;

    public MRatingActivity(PRatingActivity pRatingActivity) {
        ipRatingActivity = pRatingActivity;
    }

    @Override
    public void getReviews(String token) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.getReviews(token,mHandler);

    }


    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.GETREVIEWS_SUCCESS:
                    ReviewsResponseModel reviewsResponseModel = (ReviewsResponseModel) msg.obj;
                    ipRatingActivity.getReviewsSuccessResponse(reviewsResponseModel);
                    break;
                case APIInterface.GETREVIEWS_NO_DATA:
                    String msgg = (String) msg.obj;
                    ipRatingActivity.getReviewsNoDataResponse(msgg);
                    break;
                case APIInterface.GETREVIEWS_FAILED:
                    String message = (String) msg.obj;
                    ipRatingActivity.getReviewsFailureResponse(message);
                    break;
            }
        }
    };
}
