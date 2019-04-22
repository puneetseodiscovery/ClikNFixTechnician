package com.cliknfix.technician.homeScreen.bottomFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.bottomFragments.adapter.UpcomingJobsAdapter;
import com.cliknfix.technician.homeScreen.bottomFragments.model.BeanUpcomingJobs;
import com.cliknfix.technician.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingJobsFragment extends Fragment {
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
    ArrayList<BeanUpcomingJobs> upcomingJobsArrayList ;

    Context context;


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
        init();
        return view;
    }

    private void init() {
        upcomingJobsArrayList=new ArrayList<>();

        upcomingJobsArrayList.add(new BeanUpcomingJobs("Closed","Carpentry","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Open","Painting","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Pending","Electrical","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Hold","Plumbing","19-March-2019",R.drawable.login_logo));
        upcomingJobsArrayList.add(new BeanUpcomingJobs("Active","Computer Repair","19-March-2019",R.drawable.login_logo));


        rvUpcomingJobs.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        UpcomingJobsAdapter adapter = new UpcomingJobsAdapter(context, upcomingJobsArrayList);
        rvUpcomingJobs.setNestedScrollingEnabled(false);
        rvUpcomingJobs.setAdapter(adapter);

    }
}
