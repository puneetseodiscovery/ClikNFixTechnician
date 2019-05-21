package com.cliknfix.tech.aboutUs;


import com.cliknfix.tech.responseModels.AboutUsResponseModel;

public interface IAboutUsActivity {
    void aboutUsSuccessResponseFromPresenter(AboutUsResponseModel aboutUsResponseModel);
    void aboutUsFailureResponseFromPresenter(String message);
}
