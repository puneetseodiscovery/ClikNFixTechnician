package com.cliknfix.tech.contact;


import com.cliknfix.tech.responseModels.ContactUsResponseModel;

public interface IPContactUsActivity {
    void contactUs(String token);
    void onContactUsSuccessResponse(ContactUsResponseModel contactUsResponseModel);
    void onContactUsFailureResponse(String message);
}
