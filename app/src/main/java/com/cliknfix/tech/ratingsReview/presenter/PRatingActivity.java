package com.cliknfix.tech.ratingsReview.presenter;

import com.cliknfix.tech.ratingsReview.IRatingActivity;
import com.cliknfix.tech.ratingsReview.RatingActivity;
import com.cliknfix.tech.ratingsReview.model.IMRatingActivity;
import com.cliknfix.tech.ratingsReview.model.MRatingActivity;
import com.cliknfix.tech.responseModels.ReviewsResponseModel;

public class PRatingActivity implements IPRatingActivity {

    private IRatingActivity iRatingActivity;
    private IMRatingActivity imRatingActivity;

    public PRatingActivity(RatingActivity ratingActivity) {
        iRatingActivity = ratingActivity;
        imRatingActivity = new MRatingActivity(this);
    }

    @Override
    public void getReviews(String token) {
        imRatingActivity.getReviews(token);
    }

    @Override
    public void getReviewsSuccessResponse(ReviewsResponseModel reviewsResponseModel) {
        iRatingActivity.getReviewsSuccessResponseFromPresenter(reviewsResponseModel);
    }

    @Override
    public void getReviewsFailureResponse(String message) {
        iRatingActivity.getReviewsFailureResponseFromPresenter(message);
    }

    @Override
    public void getReviewsNoDataResponse(String msgg) {
        iRatingActivity.getReviewsNoDataResponseFromPresenter(msgg);
    }
}
