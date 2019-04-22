package com.cliknfix.technician.customerProfile;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.chat.ChatActivity;
import com.cliknfix.technician.completeJob.CompleteJobActivity;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingCustomerProfileFragment extends Fragment implements View.OnClickListener {

    public static final String TAG_UPCOMING_CUSTOMER_PROFILE_FRAGMENT = "UpcomingCustomerProfileFragment";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_phone_text)
    TextView tvPhoneText;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_age_text)
    TextView tvAgeText;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.tv_bld_grp_text)
    TextView tvBldGrpText;
    @BindView(R.id.et_bld_grp)
    EditText etBldGrp;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_track)
    Button btnTrack;
    @BindView(R.id.btn_start_job)
    Button btnStartJob;

    Context context;

    public UpcomingCustomerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_customer_profile, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvUserNameText.setTypeface(Utility.typeFaceForText(getContext()));
        etUserName.setTypeface(Utility.typeFaceForSemiBoldText(getContext()));
        tvEmailText.setTypeface(Utility.typeFaceForText(getContext()));
        etEmail.setTypeface(Utility.typeFaceForText(getContext()));
        tvPhoneText.setTypeface(Utility.typeFaceForText(getContext()));
        etPhone.setTypeface(Utility.typeFaceForText(getContext()));
        tvAgeText.setTypeface(Utility.typeFaceForText(getContext()));
        etAge.setTypeface(Utility.typeFaceForText(getContext()));
        tvBldGrpText.setTypeface(Utility.typeFaceForText(getContext()));
        etBldGrp.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));
        btnTrack.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnStartJob.setTypeface(Utility.typeFaceForBoldText(getContext()));

        ivBack.setOnClickListener(this);
        ivMsg.setOnClickListener(this);
        ivPhone.setOnClickListener(this);
        btnTrack.setOnClickListener(this);
        btnStartJob.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_msg:
                startActivity(new Intent((HomeScreenActivity)context, ChatActivity.class));
                break;
            case R.id.iv_phone:
                break;
            case R.id.btn_track:
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.btn_start_job:
                startActivity(new Intent((HomeScreenActivity)context, CompleteJobActivity.class));
                break;
            case R.id.iv_back:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
