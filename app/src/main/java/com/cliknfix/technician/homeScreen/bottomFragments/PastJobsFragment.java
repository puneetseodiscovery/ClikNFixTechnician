package com.cliknfix.technician.homeScreen.bottomFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.bottomFragments.adapter.PastJobsAdapter;
import com.cliknfix.technician.homeScreen.bottomFragments.model.BeanPastJobs;
import com.cliknfix.technician.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastJobsFragment extends Fragment {

    public static String TAG_PAST_Jobs_FRAGMENT = "PastJobsFragment";

    @BindView(R.id.rv_past_jobs)
    RecyclerView rvPastJobs;
    ArrayList<BeanPastJobs> pastJobsArrayList ;

    Context context;

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
        init();
        return view;
    }

    private void init() {
        pastJobsArrayList=new ArrayList<>();

        pastJobsArrayList.add(new BeanPastJobs("Closed","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Open","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Pending","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Hold","Carpentry","19-March-2019",R.drawable.login_logo));
        pastJobsArrayList.add(new BeanPastJobs("Active","Carpentry","19-March-2019",R.drawable.login_logo));


        rvPastJobs.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        PastJobsAdapter adapter = new PastJobsAdapter(context, pastJobsArrayList);
        rvPastJobs.setNestedScrollingEnabled(false);
        rvPastJobs.setAdapter(adapter);

    }

}
