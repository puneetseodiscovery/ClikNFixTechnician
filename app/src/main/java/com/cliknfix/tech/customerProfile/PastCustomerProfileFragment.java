package com.cliknfix.tech.customerProfile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliknfix.tech.R;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PastCustomerProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String TAG_PAST_CUSTOMER_PROFILE_FRAGMENT = "PastCustomerProfileFragment";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.tv_username_text)
    TextView tvUserNameText;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.tv_email_text)
    TextView tvEmailText;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_job_date_text)
    TextView tvJobDateText;
    @BindView(R.id.et_job_date)
    EditText etJobDate;
    @BindView(R.id.tv_job_type_text)
    TextView tvJobTypeText;
    @BindView(R.id.et_job_type)
    EditText etJobType;
    @BindView(R.id.tv_payable_amt_text)
    TextView tvPayableAmtText;
    @BindView(R.id.et_payable_amt)
    EditText etPayableAmt;
    @BindView(R.id.tv_address_text)
    TextView tvAddressText;
    @BindView(R.id.et_address)
    EditText etAddress;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastCustomerProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastCustomerProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastCustomerProfileFragment newInstance(String param1, String param2) {
        PastCustomerProfileFragment fragment = new PastCustomerProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past_customer_profile, container, false);
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
        tvJobDateText.setTypeface(Utility.typeFaceForText(getContext()));
        etJobDate.setTypeface(Utility.typeFaceForText(getContext()));
        tvJobTypeText.setTypeface(Utility.typeFaceForText(getContext()));
        etJobType.setTypeface(Utility.typeFaceForText(getContext()));
        tvPayableAmtText.setTypeface(Utility.typeFaceForText(getContext()));
        etPayableAmt.setTypeface(Utility.typeFaceForText(getContext()));
        tvAddressText.setTypeface(Utility.typeFaceForText(getContext()));
        etAddress.setTypeface(Utility.typeFaceForText(getContext()));

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
    }
}
