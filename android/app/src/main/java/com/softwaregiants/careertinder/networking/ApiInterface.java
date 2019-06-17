package com.softwaregiants.careertinder.networking;

import com.softwaregiants.careertinder.models.CandidateProfileModel;
import com.softwaregiants.careertinder.models.JobOpeningModel;
import com.softwaregiants.careertinder.models.LoginModel;
import com.softwaregiants.careertinder.models.SignUpModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("api/signUp")
    Call<ResponseBody> signUp(@Body SignUpModel signUpModel);

    @POST("api/login")
    Call<ResponseBody> login(@Body LoginModel loginModel);

    @POST("candidate/create/{authToken}")
    Call<ResponseBody> postSignUp(@Body CandidateProfileModel candidateProfileModel, @Path("authToken") String auth_code);

    @POST("api/createProfileCompany/{authToken}")
    Call<ResponseBody> addNewJobOpening(@Body JobOpeningModel jobOpeningModel, @Path("authToken") String auth_code);

    @GET("api/listJobsCompany/{authToken}")
    Call<ResponseBody> getJobOpenings(@Path("authToken") String auth_code);

    @GET("api/allJobsCompany")
    Call<ResponseBody> getMatchedJobOpenings();

    @GET("candidate/all")
    Call<ResponseBody> getMatchedCandidates();

    @PUT("api/updateJobsCompany/{authToken}")
    Call<ResponseBody> updateJobOpening(@Body JobOpeningModel jobOpeningModel, @Path("authToken") String auth_code);

    @GET("candidate/display/{authToken}")
    Call<ResponseBody> getCandidateProfile(@Path("authToken") String auth_code);

}
