package com.softwaregiants.careertinder.networking;

import com.softwaregiants.careertinder.models.BaseBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("5cd0aab83300006200b12587")
    Call<BaseBean> getSuccessCode();

}
