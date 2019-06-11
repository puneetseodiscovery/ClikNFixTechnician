package com.cliknfix.tech.homeScreen.bottomFragments;


import android.app.ProgressDialog;
import android.content.Context;
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

import com.cliknfix.tech.R;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PUserProfileFragment;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;
import com.cliknfix.tech.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener,IUserProfileFragment {

    public static String TAG_USER_PROFILE_FRAGMENT = "UserProfileFragment";

    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.cam)
    CircleImageView CIcam;
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

    IPUserProfileFragment ipUserProfileFragment;
    ProgressDialog progressDialog;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        ipUserProfileFragment = new PUserProfileFragment(this);
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

        progressDialog = Utility.showLoader(getContext());
        ipUserProfileFragment.getUserProfile(Utility.getUserId(),Utility.getToken());

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
                //etEmail.setFocusableInTouchMode(true);
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
                //etEmail.setFocusable(false);
                etEmail.setFocusableInTouchMode(false);
                etLabour.setFocusable(false);
                etLabour.setFocusableInTouchMode(false);
                etAge.setFocusable(false);
                etAge.setFocusableInTouchMode(false);
                etAddress.setFocusable(false);
                etAddress.setFocusableInTouchMode(false);
                ivVerifiedDoc.setEnabled(false);
                mgr.hideSoftInputFromWindow(v.getWindowToken(),0);
                progressDialog = Utility.showLoader(getContext());
                ipUserProfileFragment.saveUserProfile(etUserName.getText().toString().trim(),
                        etAge.getText().toString().trim(),
                        etAddress.getText().toString().trim(),
                        Utility.getToken());
                break;
            case R.id.iv_verfied_doc:
                Toast.makeText(getActivity(), "Coming Soon!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cam:
                Toast.makeText(getActivity(), "Coming Soon!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getUserProfileSuccessFromPresenter(UserProfileResponseModel userProfileResponseModel) {
        progressDialog.dismiss();
        etUserName.setText(userProfileResponseModel.getData().get(0).getName());
        etEmail.setText(userProfileResponseModel.getData().get(0).getEmail());
        etLabour.setText(userProfileResponseModel.getData().get(0).getServiceCategory());
        etAge.setText(userProfileResponseModel.getData().get(0).getAge());
        etAddress.setText(userProfileResponseModel.getData().get(0).getAddress());
    }

    @Override
    public void getUserProfileFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUserProfileSuccessFromPresenter(SaveUserProfileResponseModel saveUserProfileResponseModel) {
        progressDialog.dismiss();
        etUserName.setText(saveUserProfileResponseModel.getData().get(0).getName());
        etEmail.setText(saveUserProfileResponseModel.getData().get(0).getEmail());
        etLabour.setText(saveUserProfileResponseModel.getData().get(0).getPhone());
        etAge.setText(saveUserProfileResponseModel.getData().get(0).getAge());
        etAddress.setText(saveUserProfileResponseModel.getData().get(0).getAddress());
    }

    @Override
    public void saveUserProfileFailureFromPresenter(String msgg) {

    }
}
