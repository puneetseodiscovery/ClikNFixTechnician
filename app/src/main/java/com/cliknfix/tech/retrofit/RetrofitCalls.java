package com.cliknfix.tech.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cliknfix.tech.base.MyApp;
import com.cliknfix.tech.responseModels.AboutUsResponseModel;
import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;
import com.cliknfix.tech.responseModels.ContactUsResponseModel;
import com.cliknfix.tech.responseModels.LoginResponseModel;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;
import com.cliknfix.tech.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.tech.responseModels.ReviewsResponseModel;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;
import com.cliknfix.tech.util.PreferenceHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    private APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void loginUser(String email, String password, double lat, double lng, String device_token, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.loginUser(email,password,lat,lng,device_token);
        Log.e("email","" + email);
        Log.e("password","" + password);
        Log.e("lat","" + lat);
        Log.e("lng","" + lng);
        Log.e("device_token","" + device_token);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.body() != null) {
                    Log.e("Status().code","" + response.code());
                    if (response.body().getStatus().equalsIgnoreCase("200")) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        String token = response.body().getData().get(0).getRememberToken();
                        int id = response.body().getData().get(0).getId();
                        Log.d("+++++++++", "++ access token++" + token);
                        Log.d("+++++++++", "++ id++" + id);
                        new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, token);
                        new PreferenceHandler().writeInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, id);
                        String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        //int userId = new PreferenceHandler().readInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, 0);
                        Log.d("+++++++++", "++ access token read++" + mLoginToken);
                        //Log.d("+++++++++", "++ id read++" + userId);
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
                Log.e("Error msg","" + t.getMessage());
                mHandler.sendMessage(message);
            }
        });

    }

    public void getUserProfile(int technician_id, String token, final Handler mHandler) {
        Log.e("technician_id","" + technician_id);
        Log.e("token","" + token);
        final Message message = new Message();
        Call<UserProfileResponseModel> call = apiInterface.getUserProfile(token,technician_id);
        call.enqueue(new Callback<UserProfileResponseModel>() {
            @Override
            public void onResponse(Call<UserProfileResponseModel> call, Response<UserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.USER_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.USER_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.USER_PROFILE_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void saveUserProfile(String name, String age, String address, String token, final Handler mHandler) {
        final Message message = new Message();
        Call<SaveUserProfileResponseModel> call = apiInterface.saveUserProfile(name,age,address,token);
        call.enqueue(new Callback<SaveUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<SaveUserProfileResponseModel> call, Response<SaveUserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.SAVE_USER_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SAVE_USER_PROFILE_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<SaveUserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.SAVE_USER_PROFILE_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void acceptRejectJob(String user_id , String user_query , String status , String token, final Handler mHandler) {
        final Message message = new Message();
        Call<AcceptRejectResponseModel> call = apiInterface.acceptRejectjob(user_id ,user_query ,status ,token);
        call.enqueue(new Callback<AcceptRejectResponseModel>() {
            @Override
            public void onResponse(Call<AcceptRejectResponseModel> call, Response<AcceptRejectResponseModel> response) {
                Log.e("response","" + response);
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.ACCEPT_REJECT_JOB_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ACCEPT_REJECT_JOB_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<AcceptRejectResponseModel> call, Throwable t) {
                message.what = apiInterface.USER_PROFILE_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }


    public void contactUs(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<ContactUsResponseModel> call = apiInterface.contactUS(token);
        call.enqueue(new Callback<ContactUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactUsResponseModel> call, Response<ContactUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.CONTACT_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CONTACT_US_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<ContactUsResponseModel> call, Throwable t) {
                message.what = apiInterface.CONTACT_US_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void privacyPolicy(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<PrivacyPolicyResponseModel> call = apiInterface.privacyPolicy(token);
        call.enqueue(new Callback<PrivacyPolicyResponseModel>() {
            @Override
            public void onResponse(Call<PrivacyPolicyResponseModel> call, Response<PrivacyPolicyResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.PRIVACY_POLICY_SUCCESSS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PRIVACY_POLICY_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<PrivacyPolicyResponseModel> call, Throwable t) {
                message.what = apiInterface.PRIVACY_POLICY_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void aboutUs(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<AboutUsResponseModel> call = apiInterface.aboutUs(token);
        call.enqueue(new Callback<AboutUsResponseModel>() {
            @Override
            public void onResponse(Call<AboutUsResponseModel> call, Response<AboutUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.ABOUT_US_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ABOUT_US_FAILURE;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<AboutUsResponseModel> call, Throwable t) {
                message.what = apiInterface.ABOUT_US_FAILURE;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void getUpcomingJobsList(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<UpcomingJobsResponseModel> call = apiInterface.getUpcomingJobsList(token);
        call.enqueue(new Callback<UpcomingJobsResponseModel>() {
            @Override
            public void onResponse(Call<UpcomingJobsResponseModel> call, Response<UpcomingJobsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.GETUPCOMINGJOBSLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else if (response.body().getStatus().equals("401")) {
                        message.what = apiInterface.GETUPCOMINGJOBSLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETUPCOMINGJOBSLIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<UpcomingJobsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETUPCOMINGJOBSLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void getPastJobsList(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<PastJobsResponseModel> call = apiInterface.getPastJobsList(token);
        call.enqueue(new Callback<PastJobsResponseModel>() {
            @Override
            public void onResponse(Call<PastJobsResponseModel> call, Response<PastJobsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.GETPASTJOBSLIST_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else if (response.body().getStatus().equals("401")) {
                        message.what = apiInterface.GETPASTJOBSLIST_NO_DATA;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETPASTJOBSLIST_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<PastJobsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETPASTJOBSLIST_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }

    public void getReviews(String token, final Handler mHandler) {
        final Message message = new Message();
        Call<ReviewsResponseModel> call = apiInterface.getReviews(token);
        call.enqueue(new Callback<ReviewsResponseModel>() {
            @Override
            public void onResponse(Call<ReviewsResponseModel> call, Response<ReviewsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("200")) {
                        message.what = apiInterface.GETREVIEWS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else if (response.body().getStatus().equals("401")) {
                        message.what = apiInterface.GETREVIEWS_NO_DATA;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.GETREVIEWS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }
            @Override
            public void onFailure(Call<ReviewsResponseModel> call, Throwable t) {
                message.what = apiInterface.GETREVIEWS_FAILED;
                message.obj = t.getMessage();
                Log.d("+++++","++ t message ++"+t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }
}