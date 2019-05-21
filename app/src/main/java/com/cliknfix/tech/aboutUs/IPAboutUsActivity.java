package com.cliknfix.tech.aboutUs;


import com.cliknfix.tech.responseModels.AboutUsResponseModel;

public interface IPAboutUsActivity {
    void aboutUs(String token);
    void onAboutUsSuccessResponse(AboutUsResponseModel aboutUsResponseModel);
    void onAboutUsFailureResponse(String message);
}
