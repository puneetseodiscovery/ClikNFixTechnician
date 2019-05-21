package com.cliknfix.tech.homeScreen.bottomFragments.model;

public interface IMUserProfileFragment {
    public void getUserProfile(int id, String token);
    void saveUserProfile(String name, String age, String address, String token);
}
