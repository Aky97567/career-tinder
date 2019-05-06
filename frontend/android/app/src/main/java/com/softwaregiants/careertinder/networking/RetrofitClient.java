package com.softwaregiants.careertinder.networking;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.utilities.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements Callback<BaseBean> {

    private static Retrofit retrofit;
    private ApiResponseCallback mApiResponseCallBack;
    public ApiInterface mApiInterface;
    private String TAG = RetrofitClient.class.getSimpleName();

    public static RetrofitClient getRetrofitClient(ApiResponseCallback mApiResponseCallBack) {
        if ( retrofit == null ) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RetrofitClient mRetrofitClient = new RetrofitClient();
        mRetrofitClient.mApiInterface = retrofit.create(ApiInterface.class);
        mRetrofitClient.mApiResponseCallBack = mApiResponseCallBack;
        return mRetrofitClient;
    }

    @Override
    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
        if (response.isSuccessful()) {
            Log.d(TAG, response.toString());
            mApiResponseCallBack.onSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<BaseBean> call, Throwable t) {
        Log.e(TAG, t.toString());
        mApiResponseCallBack.onFailure(t);
    }

}
