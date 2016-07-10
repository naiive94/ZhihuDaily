package com.naiive.zhihudaily.common.api;

import com.naiive.zhihudaily.common.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangzhe on 16/5/22.
 */
public class ZhihuRetrofit {

    private OkHttpClient okHttpClient;

    public ZhihuRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public ZhihuApi getZhihuApi() {
        return createRetrofit(okHttpClient, Constant.BASE_URL).create(ZhihuApi.class);
    }

    public CommonApi getCommonApi(String url){
        return createRetrofit(okHttpClient,url).create(CommonApi.class);
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient, String host) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(host)
                .build();
    }
}
