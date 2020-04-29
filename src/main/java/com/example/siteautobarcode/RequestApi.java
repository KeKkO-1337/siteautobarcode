package com.example.siteautobarcode;

import com.example.siteautobarcode.POJO.MePOJO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RequestApi {
    @GET("b2c/me")
    Call<MePOJO> getInfo(@Header("Authorization") String authorization);
    @GET("b2c/me/account/transactions?firstResult=0&maxResults=10&orderField=date:desc&type=ER")
    Call<ResponseBody> getTransaction(@Header("Authorization") String authorization);
}
