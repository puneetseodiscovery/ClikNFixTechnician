package com.cliknfix.tech.ratingsReview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.ratingsReview.adapter.RatingAdapter;
import com.cliknfix.tech.ratingsReview.model.BeanRating;
import com.cliknfix.tech.ratingsReview.presenter.IPRatingActivity;
import com.cliknfix.tech.ratingsReview.presenter.PRatingActivity;
import com.cliknfix.tech.responseModels.ReviewsResponseModel;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingActivity extends BaseClass implements IRatingActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    RatingBar ratingBar;
    @BindView(R.id.rv_ratings)
    RecyclerView rvRating;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.ll_rating)
    LinearLayout llRating;
    ArrayList<BeanRating> ratingArrayList;

    IPRatingActivity ipRatingActivity;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        ButterKnife.bind(this);
        ipRatingActivity = new PRatingActivity(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        ratingArrayList=new ArrayList<>();

        /*ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Rahul",4,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Raj",(float) 4.5,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Tarun",3,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Vishal",3,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Aman",2,getResources().getString(R.string.accept_reject_text)));


        rvRating.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        RatingAdapter adapter = new RatingAdapter(this, ratingArrayList);
        rvRating.setNestedScrollingEnabled(false);
        rvRating.setAdapter(adapter);*/
        progressDialog= Utility.showLoader(this);
        ipRatingActivity.getReviews(Utility.getToken());
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }

    @Override
    public void getReviewsSuccessResponseFromPresenter(ReviewsResponseModel reviewsResponseModel) {
        progressDialog.dismiss();
        tvNoData.setVisibility(View.GONE);
        llRating.setVisibility(View.VISIBLE);
        rvRating.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        RatingAdapter adapter = new RatingAdapter(this,reviewsResponseModel.getData());
        rvRating.setNestedScrollingEnabled(false);
        rvRating.setAdapter(adapter);
    }

    @Override
    public void getReviewsFailureResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getReviewsNoDataResponseFromPresenter(String msgg) {
        progressDialog.dismiss();
        tvNoData.setText(msgg);
        tvNoData.setVisibility(View.VISIBLE);
        llRating.setVisibility(View.GONE);
    }
}
