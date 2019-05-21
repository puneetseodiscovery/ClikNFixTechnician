package com.cliknfix.tech.retrofit;

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

import retrofit2.Call;
import retrofit2.http.Body;
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
}
