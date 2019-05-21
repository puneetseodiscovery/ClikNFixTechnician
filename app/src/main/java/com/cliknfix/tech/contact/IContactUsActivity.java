package com.cliknfix.tech.contact;


import com.cliknfix.tech.responseModels.ContactUsResponseModel;

public interface IContactUsActivity {
    void contactUsFailureResponseFromPresenter(String message);
    void contactUsSuccessResponseFromPresenter(ContactUsResponseModel contactUsResponseModel);
}
