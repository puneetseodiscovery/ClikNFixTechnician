package com.cliknfix.tech.homeScreen.bottomFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.homeScreen.bottomFragments.adapter.EarningAdapter;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanEarnings;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPEarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PEarningFragment;
import com.cliknfix.tech.responseModels.EarningsResponseModel;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EarningFragment extends Fragment implements IEarningFragment {

    public static String TAG_EARNING_FRAGMENT = "EarningFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_total_earning_text)
    TextView tvTotalEarningText;
    @BindView(R.id.tv_total_earning)
    TextView tvTotalEarning;
    @BindView(R.id.rv_earning)
    RecyclerView rvEarning;
    ArrayList<BeanEarnings> earningArrayList ;

    IPEarningFragment ipEarningFragment;
    ProgressDialog progressDialog;

    @BindView(R.id.ll_earning)
    LinearLayout llEarning;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    Context context;

    public EarningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EarningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EarningFragment newInstance(String param1, String param2) {
        EarningFragment fragment = new EarningFragment();
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
        View view = inflater.inflate(R.layout.fragment_earning, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ipEarningFragment = new PEarningFragment(this);
        init();
        return view;
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        tvTotalEarning.setTypeface(Utility.typeFaceForText(getContext()));
        tvTotalEarningText.setTypeface(Utility.typeFaceForText(getContext()));

        earningArrayList=new ArrayList<>();

        /*earningArrayList.add(new BeanEarnings("Closed","Carpentry","19-March-2019","$20",R.drawable.login_logo));
        earningArrayList.add(new BeanEarnings("Open","Carpentry","19-March-2019","$30",R.drawable.login_logo));
        earningArrayList.add(new BeanEarnings("Pending","Carpentry","19-March-2019","$20",R.drawable.login_logo));
        earningArrayList.add(new BeanEarnings("Hold","Carpentry","19-March-2019","$10",R.drawable.login_logo));
        earningArrayList.add(new BeanEarnings("Active","Carpentry","19-March-2019","$20",R.drawable.login_logo));*/

        getEarnings();

    }

    private void getEarnings() {
        progressDialog = Utility.showLoader(getContext());
        ipEarningFragment.getEarnings(Utility.getToken());

    }

    @Override
    public void getEarningsSuccessFromPresenter(EarningsResponseModel earningsResponseModel) {
        progressDialog.dismiss();
        tvNoData.setVisibility(View.GONE);
        llEarning.setVisibility(View.VISIBLE);
        /*rvPastJobs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        PastJobsAdapter adapter = new PastJobsAdapter(context, pastJobsResponseModel.getData());
        rvPastJobs.setNestedScrollingEnabled(false);
        rvPastJobs.setAdapter(adapter);*/

        rvEarning.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        EarningAdapter adapter = new EarningAdapter(context, earningsResponseModel.getData());
        rvEarning.setNestedScrollingEnabled(false);
        rvEarning.setAdapter(adapter);
    }

    @Override
    public void getEarningsFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, ""+ message, Toast.LENGTH_SHORT).show();
    }
}
