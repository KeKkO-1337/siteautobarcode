package com.example.siteautobarcode;

import com.example.siteautobarcode.POJO.MePOJO;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

public class GetBalance {

    public static OkHttpClient getUnsafeOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();

        return okHttpClient;
    }

    public MePOJO getBalance(String token)
    {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://moy.magnit.ru/")
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MePOJO mePOJO = null;
        Call<MePOJO> call = mRetrofit.create(RequestApi.class).getInfo("Bearer " + token);
        try {
            Response<MePOJO> response = call.execute();
            if(response.code() == 200)
                mePOJO = response.body();
            else
                System.out.println("Error: " + response.code() + " " + response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return mePOJO;
    }
}
