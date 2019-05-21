package com.cliknfix.tech.customerProfile.model;

import com.cliknfix.tech.customerProfile.presenter.IPUpcomingCustomerProfileFragment;
import com.cliknfix.tech.customerProfile.presenter.PUpcomingCustomerProfileFragment;

public class MUpcomingCustomerProfileFragment implements IMUpcomingCustomerProfileFragment {

    IPUpcomingCustomerProfileFragment ipUpcomingCustomerProfileFragment;

    public MUpcomingCustomerProfileFragment(PUpcomingCustomerProfileFragment pUpcomingCustomerProfileFragment) {
        this.ipUpcomingCustomerProfileFragment = pUpcomingCustomerProfileFragment;
    }

}
