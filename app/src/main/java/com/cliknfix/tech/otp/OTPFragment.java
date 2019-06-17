package com.cliknfix.tech.otp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.completeJob.CompleteJobActivity;
import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_DEL;


public class OTPFragment extends Fragment implements IOTPFragment {

    public static String TAG_OTP_FRAGMENT = "OTPFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_get_otp_text)
    TextView tvGetOTPText;
    @BindView(R.id.tv_get_otp_p)
    TextView tvGetOTPPText;
    @BindView(R.id.et_otp1)
    EditText etOTP1;
    @BindView(R.id.et_otp2)
    EditText etOTP2;
    @BindView(R.id.et_otp3)
    EditText etOTP3;
    @BindView(R.id.et_otp4)
    EditText etOTP4;
    @BindView(R.id.btn_start_job)
    Button btnStartJob;

    Context context;
    ProgressDialog progressDialog;
    IPOTPFragment ipotpFragment;
    String labourRate;

    public OTPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OTPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OTPFragment newInstance(String param1, String param2) {
        OTPFragment fragment = new OTPFragment();
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
        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        ipotpFragment = new POTPFragment(this);
        init();
        return view;
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvGetOTPText.setTypeface(Utility.typeFaceForText(getContext()));
        tvGetOTPPText.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnStartJob.setTypeface(Utility.typeFaceForBoldText(getContext()));

        labourRate = getArguments().getString("labour_rate");
        btnStartJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (etOTP1.getText().toString().length()>0 && etOTP2.getText().toString().length()>0
            && etOTP3.getText().toString().length()>0 && etOTP4.getText().toString().length()>0 ) {
                String otp = etOTP1.getText().toString() + etOTP2.getText().toString() +
                        etOTP3.getText().toString() + etOTP4.getText().toString();
                    progressDialog = Utility.showLoader(context);
                    ipotpFragment.submitOTP(otp,Utility.getToken());
            } else {
                if (etOTP1.getText().toString().length()==0 || etOTP2.getText().toString().length()==0
                    || etOTP3.getText().toString().length()==0 || etOTP4.getText().toString().length()==0)
                {
                    etOTP1.setError("Enter OTP.");
                    etOTP1.requestFocus();
                }
            }
            }
        });

        setRequestFocus();
    }

    private void setRequestFocus() {
        etOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP1.getText().toString().length()>0)
                    etOTP2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP2.getText().toString().length()>0)
                    etOTP3.requestFocus();

                if(etOTP2.getText().toString().length() == 0)
                    etOTP1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP3.getText().toString().length()>0)
                    etOTP4.requestFocus();

                if(etOTP3.getText().toString().length() == 0)
                    etOTP2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etOTP4.getText().toString().length() == 0)
                    etOTP3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent evnet) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KEYCODE_DEL) {
                    //this is for backspace
                    if(etOTP4.getText().toString().length() == 0)
                        etOTP3.requestFocus();
                }
                return false;
            }
        });

        etOTP3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent evnet) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KEYCODE_DEL) {
                    //this is for backspace
                    if(etOTP3.getText().toString().length() == 0)
                        etOTP2.requestFocus();
                }
                return false;
            }
        });

        etOTP2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent evnet) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KEYCODE_DEL) {
                    //this is for backspace
                    if(etOTP2.getText().toString().length() == 0)
                        etOTP1.requestFocus();
                }
                return false;
            }
        });
    }



    @Override
    public void onSubmitOTPSuccessFromPresenter(SubmitOTPResponseModel submitOTPResponseModel) {
        progressDialog.dismiss();
        new PreferenceHandler().writeStartJobBoolean(MyApp.getInstance().getApplicationContext(), PreferenceHandler.START_JOB_OTP_FILL, true);
        Intent intent = new Intent(context, CompleteJobActivity.class);
        intent.putExtra("labour_rate",labourRate);
        startActivity(intent);
    }

    @Override
    public void onSubmitOTPFailedFromPresenter(String msgg) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + msgg, Toast.LENGTH_SHORT).show();
    }
}
