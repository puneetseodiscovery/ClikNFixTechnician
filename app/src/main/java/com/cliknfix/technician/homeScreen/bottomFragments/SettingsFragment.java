package com.cliknfix.technician.homeScreen.bottomFragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.changePassword.ChangePasswordActivity;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.ratingsReview.RatingActivity;
import com.cliknfix.technician.util.AppConstants;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    public static String TAG_SETTINGS_FRAGMENT = "SettingsFragment";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.tv_profile_settings)
    TextView tvProfileSettings;
    @BindView(R.id.tv_payments)
    TextView tvPayments;
    @BindView(R.id.tv_my_earnings)
    TextView tvMyEarnings;
    @BindView(R.id.tv_reviews)
    TextView tvReviews;
    @BindView(R.id.tv_contact_us)
    TextView tvContactUs;
    @BindView(R.id.tv_privacy_policy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    Context context;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvAboutUs.setTypeface(Utility.typeFaceForText(getContext()));
        tvProfileSettings.setTypeface(Utility.typeFaceForText(getContext()));
        tvPayments.setTypeface(Utility.typeFaceForText(getContext()));
        tvMyEarnings.setTypeface(Utility.typeFaceForText(getContext()));
        tvReviews.setTypeface(Utility.typeFaceForText(getContext()));
        tvContactUs.setTypeface(Utility.typeFaceForText(getContext()));
        tvPrivacyPolicy.setTypeface(Utility.typeFaceForText(getContext()));
        tvChangePassword.setTypeface(Utility.typeFaceForText(getContext()));
        tvLogout.setTypeface(Utility.typeFaceForText(getContext()));

        tvAboutUs.setOnClickListener(this);
        tvProfileSettings.setOnClickListener(this);
        tvPayments.setOnClickListener(this);
        tvMyEarnings.setOnClickListener(this);
        tvReviews.setOnClickListener(this);
        tvContactUs.setOnClickListener(this);
        tvPrivacyPolicy.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_about_us:
                break;
            case R.id.tv_profile_settings:
                AppConstants.USER_PROFILE_FROM_SETTINGS = true;
                ((HomeScreenActivity)getActivity()).loadFragment(new UserProfileFragment());
                break;
            case R.id.tv_payments:
                break;
            case R.id.tv_my_earnings:
                AppConstants.USER_PROFILE_FROM_SETTINGS = true;
                ((HomeScreenActivity)getActivity()).loadFragment(new EarningFragment());
                break;
            case R.id.tv_reviews:
                startActivity(new Intent((HomeScreenActivity)context, RatingActivity.class));
                break;
            case R.id.tv_contact_us:
                break;
            case R.id.tv_privacy_policy:
                break;
            case R.id.tv_change_password:
                startActivity(new Intent((HomeScreenActivity)context, ChangePasswordActivity.class));
                break;
            case R.id.tv_logout:
                break;

        }

    }

  /*  public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }*/

}
