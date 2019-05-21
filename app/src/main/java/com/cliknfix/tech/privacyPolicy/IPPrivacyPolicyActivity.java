package com.cliknfix.tech.privacyPolicy;

import com.cliknfix.tech.responseModels.PrivacyPolicyResponseModel;

public interface IPPrivacyPolicyActivity {
    void privacyPolicy(String token);
    void onPrivacyPolicySuccessResponse(PrivacyPolicyResponseModel privacyPolicyResponseModel);
    void onPrivacyPolicyFailureResponse(String message);
}
