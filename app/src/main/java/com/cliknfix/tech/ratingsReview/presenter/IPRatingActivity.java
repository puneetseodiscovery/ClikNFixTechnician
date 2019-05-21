package com.cliknfix.tech.ratingsReview.presenter;

import com.cliknfix.tech.responseModels.ReviewsResponseModel;

public interface IPRatingActivity {
    void getReviews(String token);
    void getReviewsSuccessResponse(ReviewsResponseModel reviewsResponseModel);
    void getReviewsFailureResponse(String message);
    void getReviewsNoDataResponse(String msgg);
}
