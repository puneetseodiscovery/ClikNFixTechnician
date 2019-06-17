package com.cliknfix.tech.homeScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.cliknfix.tech.R;
import com.cliknfix.tech.base.BaseClass;
import com.cliknfix.tech.customerProfile.PastCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.UpcomingCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.EarningFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.HomeFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.SettingsFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.UserProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanNotification;
import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;
import com.cliknfix.tech.util.AppConstants;
import com.cliknfix.tech.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenActivity extends BaseClass implements IHomeScreenActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    int defaultTab=0;
    String message,technicianId,userId,labourRate,phone;
    boolean doubleBackToExitPressedOnce = false;
    ProgressDialog progressDialog;
    IPHomeScreenActivity ipHomeScreenActivity;
    boolean isUpcomingCustomer;
    String category,createdAt,servicePrice;
    String upcomingCustomer;

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
        ipHomeScreenActivity = new PHomeScreenActivity(this);
        defaultTab = getIntent().getIntExtra("DefaultTab",0);
        init();

    }

    private void init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(defaultTab == 1 || AppConstants.USER_PROFILE_FROM_SETTINGS) {
            navigation.getMenu().findItem(R.id.navigation_settings).setChecked(true);
            loadFragment(new SettingsFragment());
            return;
        } else if(getIntent().getStringExtra("upcomingCustomerDetail") != null){
            loadFragment(new UpcomingCustomerProfileFragment());
        }

        loadFragment(new HomeFragment());
        firebaseUsername = String.valueOf(Utility.getUserId());
        if(getIntent().getExtras()!=null) {
            Log.e("message","" + getIntent().getStringExtra("message"));
            Log.e("technician_id","" + getIntent().getStringExtra("technician_id"));
            Log.e("user_id","" + getIntent().getStringExtra("user_id"));
            Log.e("labour_rate","" + getIntent().getStringExtra("labour_rate"));
            Log.e("user_phone","" + getIntent().getStringExtra("user_phone"));
            message = getIntent().getStringExtra("message");
            technicianId = getIntent().getStringExtra("technician_id");
            userId = getIntent().getStringExtra("user_id");
            labourRate = getIntent().getStringExtra("labour_rate");
            phone = getIntent().getStringExtra("user_phone");
            Log.e("Homescreen userId","" + userId);


        }


    }

    public BeanNotification getIntentData() {
        Log.e("Homescreen1 userId","" + userId);
        BeanNotification beanNotification = new BeanNotification();
        beanNotification.setMessage(message);
        beanNotification.setUserId(userId);
        beanNotification.setLabourRate(labourRate);
        beanNotification.setPhone(phone);
        return beanNotification;
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        //clearStack();
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

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.exit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

        } else {
            super.onBackPressed();
        }
    }

    public void fetchUserData(int userId){
        Log.e("user data","" + userId);
        progressDialog = Utility.showLoader(this);
        ipHomeScreenActivity.getCustomerProfile(userId,Utility.getToken());
        isUpcomingCustomer = true;
    }

    @Override
    public void getCustomerProfileSuccessFromPresenter(CustomerProfileResponseModel customerProfileResponseModel) {
        progressDialog.dismiss();
        Log.e("userId","" + customerProfileResponseModel.getData().get(0).getId());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(isUpcomingCustomer) {
            UpcomingCustomerProfileFragment fragment = new UpcomingCustomerProfileFragment();
            Bundle args = new Bundle();
            args.putInt("id", customerProfileResponseModel.getData().get(0).getId());
            args.putString("name", customerProfileResponseModel.getData().get(0).getName());
            args.putString("email", customerProfileResponseModel.getData().get(0).getEmail());
            args.putString("phone", customerProfileResponseModel.getData().get(0).getPhone());
            args.putString("age", customerProfileResponseModel.getData().get(0).getAge());
            args.putString("blood_group", customerProfileResponseModel.getData().get(0).getBloodGroup());
            args.putString("address", customerProfileResponseModel.getData().get(0).getAddress());
            fragment.setArguments(args);
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            PastCustomerProfileFragment fragment = new PastCustomerProfileFragment();
            Bundle args = new Bundle();
            args.putInt("id", customerProfileResponseModel.getData().get(0).getId());
            args.putString("name", customerProfileResponseModel.getData().get(0).getName());
            args.putString("email", customerProfileResponseModel.getData().get(0).getEmail());
            args.putString("jobDate", createdAt);
            args.putString("jobType", category);
            args.putString("PayableAmt", servicePrice);
            args.putString("address", customerProfileResponseModel.getData().get(0).getAddress());
            fragment.setArguments(args);
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    @Override
    public void getCustomerProfileFailureFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    public void fetchPastUserData(int userId, String category, String createdAt, String servicePrice) {
        progressDialog = Utility.showLoader(this);
        ipHomeScreenActivity.getCustomerProfile(userId,Utility.getToken());
        isUpcomingCustomer = false;
        this.category = category;
        this.createdAt = createdAt;
        this.servicePrice = servicePrice;
    }
}
