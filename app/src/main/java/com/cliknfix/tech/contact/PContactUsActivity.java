package com.cliknfix.tech.contact;


import com.cliknfix.tech.responseModels.ContactUsResponseModel;

public class PContactUsActivity implements IPContactUsActivity {

    private IContactUsActivity iContactUsActivity;
    private IMContactUsActivity imContactUsActivity;

    public PContactUsActivity(ContactUsActivity contactUsActivity) {
        iContactUsActivity = contactUsActivity;
        imContactUsActivity = new MContactUsActivity(this);
    }

    @Override
    public void contactUs(String token) {
        imContactUsActivity.contactUs(token);
    }

    @Override
    public void onContactUsSuccessResponse(ContactUsResponseModel contactUsResponseModel) {
        iContactUsActivity.contactUsSuccessResponseFromPresenter(contactUsResponseModel);
    }

    @Override
    public void onContactUsFailureResponse(String message) {
        iContactUsActivity.contactUsFailureResponseFromPresenter(message);
    }
}
