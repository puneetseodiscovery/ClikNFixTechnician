package com.cliknfix.tech.privacyPolicy;

import com.cliknfix.tech.responseModels.PrivacyPolicyResponseModel;

public interface IPrivacyPolicyActivity {
    void privacyPolicySuccessResponseFromPresenter(PrivacyPolicyResponseModel privacyPolicyResponseModel);
    void privacyPolicyFailureResponseFromPresenter(String message);
}
