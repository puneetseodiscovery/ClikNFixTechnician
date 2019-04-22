package com.cliknfix.technician.homeScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.technician.R;
import com.cliknfix.technician.homeScreen.bottomFragments.EarningFragment;
import com.cliknfix.technician.homeScreen.bottomFragments.HomeFragment;
import com.cliknfix.technician.homeScreen.bottomFragments.SettingsFragment;
import com.cliknfix.technician.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.technician.util.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    int defaultTab=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_settings:
                    loadFragment(new SettingsFragment());
                    return true;
                case R.id.navigation_past_jobs:
                    loadFragment(new EarningFragment());
                    return true;
                case R.id.navigation_user_profile:
                    AppConstants.USER_PROFILE_FROM_SETTINGS = false;
                    loadFragment(new UserProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        defaultTab = getIntent().getIntExtra("DefaultTab",0);
        init();
        if(defaultTab == 1 || AppConstants.USER_PROFILE_FROM_SETTINGS) {
            navigation.getMenu().findItem(R.id.navigation_settings).setChecked(true);
            loadFragment(new SettingsFragment());
        }
    }

    private void init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        clearStack();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        if(AppConstants.USER_PROFILE_FROM_SETTINGS)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void clearStack(){
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
