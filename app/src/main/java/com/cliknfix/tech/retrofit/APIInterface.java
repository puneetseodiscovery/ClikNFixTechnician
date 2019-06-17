package com.cliknfix.tech.retrofit;

import com.cliknfix.tech.responseModels.AboutUsResponseModel;
import com.cliknfix.tech.responseModels.AcceptRejectResponseModel;
import com.cliknfix.tech.responseModels.ChangePasswordResponseModel;
import com.cliknfix.tech.responseModels.CompleteJobResponseModel;
import com.cliknfix.tech.responseModels.ContactUsResponseModel;
import com.cliknfix.tech.responseModels.CustomerProfileResponseModel;
import com.cliknfix.tech.responseModels.EarningsResponseModel;
import com.cliknfix.tech.responseModels.ForgotPasswordResponseModel;
import com.cliknfix.tech.responseModels.LoginResponseModel;
import com.cliknfix.tech.responseModels.LogoutResponseModel;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;
import com.cliknfix.tech.responseModels.PrivacyPolicyResponseModel;
import com.cliknfix.tech.responseModels.ReviewsResponseModel;
import com.cliknfix.tech.responseModels.SaveUserProfileResponseModel;
import com.cliknfix.tech.responseModels.SubmitOTPResponseModel;
import com.cliknfix.tech.responseModels.UpcomingJobsResponseModel;
import com.cliknfix.tech.responseModels.UserProfileResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int USER_PROFILE_SUCCESS= 3;
    public static final int USER_PROFILE_FAILED= 4;
    public static final int SAVE_USER_PROFILE_SUCCESS= 5;
    public static final int SAVE_USER_PROFILE_FAILURE= 6;
    public static final int ACCEPT_REJECT_JOB_SUCCESS= 7;
    public static final int ACCEPT_REJECT_JOB_FAILURE= 8;
    public static final int CONTACT_US_SUCCESS= 9;
    public static final int CONTACT_US_FAILURE= 10;
    public static final int PRIVACY_POLICY_SUCCESSS= 11;
    public static final int PRIVACY_POLICY_FAILURE= 12;
    public static final int ABOUT_US_SUCCESS= 13;
    public static final int ABOUT_US_FAILURE= 14;
    public static final int GETUPCOMINGJOBSLIST_SUCCESS= 15;
    public static final int GETUPCOMINGJOBSLIST_NO_DATA= 16;
    public static final int GETUPCOMINGJOBSLIST_FAILED= 17;
    public static final int GETPASTJOBSLIST_SUCCESS= 18;
    public static final int GETPASTJOBSLIST_NO_DATA= 19;
    public static final int GETPASTJOBSLIST_FAILED= 20;
    public static final int GETREVIEWS_SUCCESS= 21;
    public static final int GETREVIEWS_NO_DATA= 22;
    public static final int GETREVIEWS_FAILED= 23;
    public static final int CUSTOMER_PROFILE_SUCCESS= 24;
    public static final int CUSTOMER_PROFILE_FAILED= 25;
    public static final int FILL_OTP_SUCCESS= 26;
    public static final int FILL_OTP_FAILED= 27;
    public static final int CHANGE_PASSWORD_SUCCESS= 28;
    public static final int CHANGE_PASSWORD_FAILED= 29;
    public static final int FORGOT_SUCCESS= 30;
    public static final int FORGOT_FAILED= 31;
    public static final int COMPLETE_JOB_SUCCESS= 32;
    public static final int COMPLETE_JOB_FAILED= 33;
    public static final int GET_EARNINGS_SUCCESS= 34;
    public static final int NO_JOB_DONE_YET= 35;
    public static final int GET_EARNINGS_FAILED= 36;
    public static final int LOGOUT_SUCCESS = 37;
    public static final int LOGOUT_FAILED = 38;

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/technicianlogin")
    Call<LoginResponseModel> loginUser(
        @Query("email") String email,
        @Query("password") String password,
        @Query("lat") double lat,
        @Query("lng") double lng,
        @Query("device_token") String device_token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/technicianProfile")
    Call<UserProfileResponseModel> getUserProfile(
            @Header("token") String token,
            @Query("technician_id") int technician_id);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/editTechnicianProfile")
    Call<SaveUserProfileResponseModel> saveUserProfile(
            @Query("name") String name,
            @Query("age") String age,
            @Query("address") String address,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/acceptOrRejectQuery")
    Call<AcceptRejectResponseModel> acceptRejectjob(
            @Query("user_id") String user_id,
            @Query("user_query") String user_query,
            @Query("status") String status,
            @Header("token") String token);


    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/contactUsTechnician")
    Call<ContactUsResponseModel> contactUS(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/privacyPolicyTechnician")
    Call<PrivacyPolicyResponseModel> privacyPolicy(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/aboutusTechnician")
    Call<AboutUsResponseModel> aboutUs(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/upcomingJobs")
    Call<UpcomingJobsResponseModel> getUpcomingJobsList(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/pastjobs")
    Call<PastJobsResponseModel> getPastJobsList(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/getReviews")
    Call<ReviewsResponseModel> getReviews(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/showUserProfile")
    Call<CustomerProfileResponseModel> getCustomerProfile(@Header("token") String token, @Query("user_id") int user_id);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/startJob")
    Call<SubmitOTPResponseModel> submitOTP(@Header("token") String token, @Query("otp") String otp);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/changeTechnicianPassword")
    Call<ChangePasswordResponseModel> changePassword(
            @Query("current_password") String current_password,
            @Query("your_password") String your_password,
            @Query("confirm_password") String confirm_password,
            @Header("token") String token);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("/Cliknfixx/api/forgetpasswordTechnician")
    Call<ForgotPasswordResponseModel> forgotPass(
            @Field("email") String email
    );

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/completeJob")
    Call<CompleteJobResponseModel> completeJob(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @GET("/Cliknfixx/api/totalEarning")
    Call<EarningsResponseModel> getEarnings(@Header("token") String token);

    @Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/logoutTechnician")
    Call<LogoutResponseModel> doLogout(@Query("technician_id") int technician_id);
}
