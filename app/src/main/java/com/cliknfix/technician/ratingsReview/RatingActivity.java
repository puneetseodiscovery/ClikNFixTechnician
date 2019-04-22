package com.cliknfix.technician.ratingsReview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.homeScreen.bottomFragments.adapter.EarningAdapter;
import com.cliknfix.technician.ratingsReview.adapter.RatingAdapter;
import com.cliknfix.technician.ratingsReview.model.BeanRating;
import com.cliknfix.technician.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    RatingBar ratingBar;
    @BindView(R.id.rv_ratings)
    RecyclerView rvRating;
    ArrayList<BeanRating> ratingArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        ratingArrayList=new ArrayList<>();

        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Rahul",4,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Raj",(float) 4.5,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Tarun",3,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Vishal",3,getResources().getString(R.string.accept_reject_text)));
        ratingArrayList.add(new BeanRating(R.mipmap.user_img,"Aman",2,getResources().getString(R.string.accept_reject_text)));


        rvRating.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        RatingAdapter adapter = new RatingAdapter(this, ratingArrayList);
        rvRating.setNestedScrollingEnabled(false);
        rvRating.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }
}
