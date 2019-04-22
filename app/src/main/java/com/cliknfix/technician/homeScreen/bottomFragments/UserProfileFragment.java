package com.cliknfix.technician.homeScreen.bottomFragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.util.AppConstants;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener {

    public static String TAG_USER_PROFILE_FRAGMENT = "UserProfileFragment";

    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.ll_user_profile)
    LinearLayout llUserProfile;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_labour_text)
    TextView tvLabourText;
    @BindView(R.id.et_labour)
    EditText etLabour;
    @BindView(R.id.tv_age_text)
    TextView tvAgeText;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_verfied_doc_text)
    TextView tvVerifiedDocText;
    @BindView(R.id.iv_verfied_doc)
    ImageView ivVerifiedDoc;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init(){
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvUserNameText.setTypeface(Utility.typeFaceForText(getContext()));
        etUserName.setTypeface(Utility.typeFaceForText(getContext()));
        tvEmailText.setTypeface(Utility.typeFaceForText(getContext()));
        etEmail.setTypeface(Utility.typeFaceForText(getContext()));
        tvLabourText.setTypeface(Utility.typeFaceForText(getContext()));
        etLabour.setTypeface(Utility.typeFaceForText(getContext()));
        tvAgeText.setTypeface(Utility.typeFaceForText(getContext()));
        etAge.setTypeface(Utility.typeFaceForText(getContext()));
        tvVerifiedDocText.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));

        ivEdit.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivVerifiedDoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (id){
            case R.id.iv_edit:
                ivEdit.setVisibility(View.GONE);
                ivSave.setVisibility(View.VISIBLE);
                etUserName.setFocusableInTouchMode(true);
                etEmail.setFocusableInTouchMode(true);
                etLabour.setFocusableInTouchMode(true);
                etAge.setFocusableInTouchMode(true);
                etAddress.setFocusableInTouchMode(true);
                etUserName.requestFocus();
                ivVerifiedDoc.setEnabled(true);
                mgr.showSoftInput(etUserName, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.iv_save:
                ivEdit.setVisibility(View.VISIBLE);
                ivSave.setVisibility(View.GONE);
                /*focusedView.setFocusable(false);
                focusedView.setFocusableInTouchMode(false);
                focusedView.setFocusable(true);
                focusedView.setFocusableInTouchMode(true);*/
                etUserName.setFocusable(false);
                etUserName.setFocusableInTouchMode(false);
                etEmail.setFocusable(false);
                etEmail.setFocusableInTouchMode(false);
                etLabour.setFocusable(false);
                etLabour.setFocusableInTouchMode(false);
                etAge.setFocusable(false);
                etAge.setFocusableInTouchMode(false);
                etAddress.setFocusable(false);
                etAddress.setFocusableInTouchMode(false);
                ivVerifiedDoc.setEnabled(false);
                mgr.hideSoftInputFromWindow(v.getWindowToken(),0);
               /* etUserName.clearFocus();
                etUserName.setFocusableInTouchMode(false);
                etEmail.clearFocus();
                etEmail.setFocusableInTouchMode(false);
                etLabour.clearFocus();
                etLabour.setFocusableInTouchMode(false);
                etAge.clearFocus();
                etAge.setFocusableInTouchMode(false);
                etAddress.clearFocus();
                etAddress.setFocusableInTouchMode(false);
                mgr.hideSoftInputFromWindow(v.getWindowToken(),0);*/
                break;
            case R.id.iv_verfied_doc:
                Toast.makeText(getActivity(), "Coming Soon!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
