package com.cliknfix.tech.homeScreen.bottomFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cliknfix.tech.R;
import com.cliknfix.tech.customerProfile.UpcomingCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.adapter.HomeAdapter;
import com.cliknfix.tech.util.AppConstants;
import com.cliknfix.tech.util.Utility;

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

    public void init() {
        Log.e("Home Fragment","working");
        tvTitle.setTypeface(Utility.typeFaceForBoldText(getContext()));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setCustomFont();

        Log.e("Home Fragment viewPager","" +viewPager.getCurrentItem());
        Log.e("Home Fragment tab","" +tabLayout.getSelectedTabPosition());

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0)
                    loadFragment(new UpcomingJobsFragment());
                else
                    loadFragment(new PastJobsFragment());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if(i == 0)
                    loadFragment(new UpcomingJobsFragment());
                else
                    loadFragment(new PastJobsFragment());
            }

            @Override
            public void onPageSelected(int i) {
                if(i == 0)
                    loadFragment(new UpcomingJobsFragment());
                else
                    loadFragment(new PastJobsFragment());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeAdapter homeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager());
        homeAdapter.addFragment(new UpcomingJobsFragment(), "UPCOMING");
        homeAdapter.addFragment(new PastJobsFragment(), "PAST");
        viewPager.setAdapter(homeAdapter);
    }

    public void setCustomFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Utility.typeFaceForText(context));
                }
            }
        }
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        //clearStack();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_upcoming_past, fragment);
        transaction.commit();
    }

}
