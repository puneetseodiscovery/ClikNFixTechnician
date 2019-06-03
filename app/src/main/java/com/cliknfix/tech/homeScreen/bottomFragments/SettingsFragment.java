package com.cliknfix.tech.homeScreen.bottomFragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cliknfix.tech.R;
import com.cliknfix.tech.aboutUs.AboutUsActivity;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.changePassword.ChangePasswordActivity;
import com.cliknfix.tech.contact.ContactUsActivity;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.login.LoginActivity;
import com.cliknfix.tech.privacyPolicy.PrivacyPolicyActivity;
import com.cliknfix.tech.ratingsReview.RatingActivity;
import com.cliknfix.tech.util.AppConstants;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;

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
//    @BindView(R.id.tv_payments)
//    TextView tvPayments;
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
        //tvPayments.setTypeface(Utility.typeFaceForText(getContext()));
        tvMyEarnings.setTypeface(Utility.typeFaceForText(getContext()));
        tvReviews.setTypeface(Utility.typeFaceForText(getContext()));
        tvContactUs.setTypeface(Utility.typeFaceForText(getContext()));
        tvPrivacyPolicy.setTypeface(Utility.typeFaceForText(getContext()));
        tvChangePassword.setTypeface(Utility.typeFaceForText(getContext()));
        tvLogout.setTypeface(Utility.typeFaceForText(getContext()));

        tvAboutUs.setOnClickListener(this);
        tvProfileSettings.setOnClickListener(this);
        //tvPayments.setOnClickListener(this);
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
                startActivity(new Intent((HomeScreenActivity)context, AboutUsActivity.class));
                break;
            case R.id.tv_profile_settings:
                AppConstants.USER_PROFILE_FROM_SETTINGS = true;
                ((HomeScreenActivity)getActivity()).loadFragment(new UserProfileFragment());
                break;
            /*case R.id.tv_payments:
                break;*/
            case R.id.tv_my_earnings:
                AppConstants.USER_PROFILE_FROM_SETTINGS = true;
                ((HomeScreenActivity)getActivity()).loadFragment(new EarningFragment());
                break;
            case R.id.tv_reviews:
                startActivity(new Intent((HomeScreenActivity)context, RatingActivity.class));
                break;
            case R.id.tv_contact_us:
                startActivity(new Intent((HomeScreenActivity)context, ContactUsActivity.class));
                break;
            case R.id.tv_privacy_policy:
                startActivity(new Intent((HomeScreenActivity)context, PrivacyPolicyActivity.class));
                break;
            case R.id.tv_change_password:
                startActivity(new Intent((HomeScreenActivity)context, ChangePasswordActivity.class));
                break;
            case R.id.tv_logout:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Logout");
                alertDialogBuilder
                        .setMessage("" +
                                "\nAre you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PreferenceHandler().clearSavedPrefrences(MyApp.getInstance().getApplicationContext());
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;

        }

    }

  /*  public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }*/

}
