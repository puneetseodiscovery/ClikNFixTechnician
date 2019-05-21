package com.cliknfix.tech.login;

public interface IMLoginActivity {
    void doLogin(String email, String password, double currentLatitude, double currentLongitude, String deviceId);
}
