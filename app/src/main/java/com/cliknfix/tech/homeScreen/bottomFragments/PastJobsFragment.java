package com.cliknfix.tech.homeScreen.bottomFragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.homeScreen.bottomFragments.adapter.PastJobsAdapter;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanPastJobs;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPPastJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PPastJobsFragment;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastJobsFragment extends Fragment implements IPastJobsFragment {

    public static String TAG_PAST_Jobs_FRAGMENT = "PastJobsFragment";

    @BindView(R.id.rv_past_jobs)
    RecyclerView rvPastJobs;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    ArrayList<BeanPastJobs> pastJobsArrayList ;


    Context context;
    IPPastJobsFragment ipPastJobsFragment;
    ProgressDialog progressDialog;

    public PastJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past_jobs, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ipPastJobsFragment = new PPastJobsFragment(this);
        init();
        return view;
    }

    private void init() {
        pastJobsArrayList=new ArrayList<>();

        /*pastJobsArrayList.add(new BeanPastJobs("Closed","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Open","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Pending","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Hold","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Active","Carpentry","19-March-2019",R.drawable.login_logo));*/

        getPastJobsList();

    }

    private void getPastJobsList() {
        String token = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
        Log.e("token:++++++","" + token);
        progressDialog = Utility.showLoader(getContext());
        ipPastJobsFragment.getPastJobsList(Utility.getToken());
    }

    @Override
    public void getPastJobsListSuccessFromPresenter(PastJobsResponseModel pastJobsResponseModel) {
        progressDialog.dismiss();
        tvNoData.setVisibility(View.GONE);
        rvPastJobs.setVisibility(View.VISIBLE);
        rvPastJobs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        PastJobsAdapter adapter = new PastJobsAdapter(context, pastJobsResponseModel.getData());
        rvPastJobs.setNestedScrollingEnabled(false);
        rvPastJobs.setAdapter(adapter);
    }

    @Override
    public void getPastJobsListFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noDataPastJobsListResponse(String msgg) {
        progressDialog.dismiss();
        tvNoData.setText(msgg);
        tvNoData.setVisibility(View.VISIBLE);
        rvPastJobs.setVisibility(View.GONE);
    }
}
