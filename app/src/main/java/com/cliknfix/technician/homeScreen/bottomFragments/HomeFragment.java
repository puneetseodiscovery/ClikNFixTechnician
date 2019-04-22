package com.cliknfix.technician.homeScreen.bottomFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.bottomFragments.adapter.HomeAdapter;
import com.cliknfix.technician.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static String TAG_HOME_FRAGMENT = "HomeFragment";

    Context context;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        init();
        return  view;
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        HomeAdapter homeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager());
        homeAdapter.addFragment(new UpcomingJobsFragment(), "UPCOMING");
        homeAdapter.addFragment(new PastJobsFragment(), "PAST");
        viewPager.setAdapter(homeAdapter);
    }

}
