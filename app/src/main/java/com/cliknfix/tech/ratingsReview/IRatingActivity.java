package com.cliknfix.tech.ratingsReview;

import com.cliknfix.tech.responseModels.ReviewsResponseModel;

public interface IRatingActivity {
    void getReviewsSuccessResponseFromPresenter(ReviewsResponseModel reviewsResponseModel);
    void getReviewsFailureResponseFromPresenter(String message);
    void getReviewsNoDataResponseFromPresenter(String msgg);
}
