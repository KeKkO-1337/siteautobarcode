package com.example.siteautobarcode;

import com.example.siteautobarcode.POJO.MePOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RequestApi {
    @GET("b2c/me")
    Call<MePOJO> getInfo(@Header("Authorization") String authorization);
}
