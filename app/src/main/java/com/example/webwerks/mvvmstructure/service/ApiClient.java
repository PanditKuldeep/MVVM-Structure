package com.example.webwerks.mvvmstructure.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/";
    private Retrofit retrofit;
    private static ApiClient apiClient;


    private ApiClient(){
        retrofit = getRetrofitInstance();
    }

    public static ApiClient getInstance(){

        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    private Retrofit getRetrofitInstance(){

        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private OkHttpClient getClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return httpClient.build();
    }

    public ApiServices getApiServices(){
        ApiServices services = retrofit.create(ApiServices.class);

        return services;
    }
}
