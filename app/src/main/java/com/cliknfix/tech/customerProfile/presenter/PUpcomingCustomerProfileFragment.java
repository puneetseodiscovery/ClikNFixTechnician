package com.cliknfix.tech.customerProfile.presenter;

import com.cliknfix.tech.customerProfile.IUpcomingCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.UpcomingCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.model.IMUpcomingCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.model.MUpcomingCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.bottomFragments.UpcomingJobsFragment;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;

public class PUpcomingCustomerProfileFragment implements IPUpcomingCustomerProfileFragment {

    IUpcomingCustomerProfileFragment iUpcomingCustomerProfileFragment;
    IMUpcomingCustomerProfileFragment imUpcomingCustomerProfileFragment;

    public PUpcomingCustomerProfileFragment(UpcomingCustomerProfileFragment upcomingCustomerProfileFragment) {
        this.iUpcomingCustomerProfileFragment = upcomingCustomerProfileFragment;
        this.imUpcomingCustomerProfileFragment = new MUpcomingCustomerProfileFragment(this);
    }

    @Override
    public void getUserProfile(int userId, String token) {

    }
}
