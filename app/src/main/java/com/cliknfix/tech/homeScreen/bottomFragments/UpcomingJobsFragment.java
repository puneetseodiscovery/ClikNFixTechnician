package com.cliknfix.tech.homeScreen.bottomFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.acceptRejectJob.AcceptRejectJobFragment;
import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.homeScreen.bottomFragments.adapter.UpcomingJobsAdapter;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanNotification;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanUpcomingJobs;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.IPUpcomingJobsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.presenter.PUpcomingJobsFragment;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingJobsFragment extends Fragment implements IUpcomingJobsFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static String TAG_UPCOMING_JOBS_FRAGMENT = "UpcomingJobsFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_upcoming_jobs)
    RecyclerView rvUpcomingJobs;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    ArrayList<BeanUpcomingJobs> upcomingJobsArrayList ;

    Context context;
    IPUpcomingJobsFragment ipUpcomingJobsFragment;
    ProgressDialog progressDialog;

    public UpcomingJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingJobsFragment newInstance(String param1, String param2) {
        UpcomingJobsFragment fragment = new UpcomingJobsFragment();
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
        View view = inflater.inflate(R.layout.fragment_upcoming_jobs, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ipUpcomingJobsFragment = new PUpcomingJobsFragment(this);
        init();
        return view;
    }

    private void init() {
        BeanNotification beanNotification = ((HomeScreenActivity)context).getIntentData();
        if(beanNotification.getMessage()!=null && beanNotification.getUserId()!=null){
            FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
            AcceptRejectJobFragment fragment = new AcceptRejectJobFragment();
            Bundle args = new Bundle();
            args.putString("user_id", beanNotification.getUserId());
            args.putString("user_query", beanNotification.getMessage());
            fragment.setArguments(args);
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        upcomingJobsArrayList=new ArrayList<>();

        getUpcomingJobsList();
        /*upcomingJobsArrayList.add(new BeanUpcomingJobs("Closed","Carpentry","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Open","Painting","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Pending","Electrical","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Hold","Plumbing","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Active","Computer Repair","19-March-2019",R.drawable.login_logo));*/

    }

    private void getUpcomingJobsList() {
        String token = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
        Log.e("token:++++++","" + token);
        progressDialog = Utility.showLoader(getContext());
        ipUpcomingJobsFragment.getUpcomingJobsList(Utility.getToken());
    }

    @Override
    public void getUpcomingJobsListSuccessFromPresenter(UpcomingJobsResponseModel upcomingJobsResponseModel) {
        progressDialog.dismiss();
        tvNoData.setVisibility(View.GONE);
        rvUpcomingJobs.setVisibility(View.VISIBLE);
        rvUpcomingJobs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        UpcomingJobsAdapter adapter = new UpcomingJobsAdapter(context, upcomingJobsResponseModel.getData());
        rvUpcomingJobs.setNestedScrollingEnabled(false);
        rvUpcomingJobs.setAdapter(adapter);
    }

    @Override
    public void getUpcomingJobsListFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noDataUpcomingJobsListResponse(String msgg) {
        progressDialog.dismiss();
        tvNoData.setText(msgg);
        tvNoData.setVisibility(View.VISIBLE);
        rvUpcomingJobs.setVisibility(View.GONE);
    }
}
