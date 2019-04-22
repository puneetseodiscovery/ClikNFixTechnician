package com.cliknfix.technician.acceptRejectJob;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.customerProfile.UpcomingCustomerProfileFragment;
import com.cliknfix.technician.homeScreen.HomeScreenActivity;
import com.cliknfix.technician.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AcceptRejectJobFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_acc_rej_text)
    TextView tvAccRejText;
    @BindView(R.id.iv_back)
    RelativeLayout ivBack;
    @BindView(R.id.btn_accept)
    Button btnAccept;
    @BindView(R.id.btn_reject)
    Button btnReject;

    Context context;

    public AcceptRejectJobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcceptRejectJobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcceptRejectJobFragment newInstance(String param1, String param2) {
        AcceptRejectJobFragment fragment = new AcceptRejectJobFragment();
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
        View view = inflater.inflate(R.layout.fragment_accept_reject_job, container, false);
        ButterKnife.bind(this,view);
        context = getContext();
        init();
        return view;
    }

    public void init() {
        tvTitle.setText(getArguments().getString("category"));
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvAccRejText.setTypeface(Utility.typeFaceForText(getContext()));
        btnAccept.setTypeface(Utility.typeFaceForBoldText(getContext()));
        btnReject.setTypeface(Utility.typeFaceForBoldText(getContext()));

        ivBack.setOnClickListener(this);
        btnAccept.setOnClickListener(this);
        btnReject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_accept:
                loadCustomerFragment();
                break;
            case R.id.btn_reject:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack(null, ((HomeScreenActivity) getActivity()).getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.iv_back:
                ((HomeScreenActivity) getActivity()).getSupportFragmentManager().popBackStack(null, ((HomeScreenActivity) getActivity()).getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }

    public void loadCustomerFragment() {
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        UpcomingCustomerProfileFragment fragment = new UpcomingCustomerProfileFragment();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
