package com.morse.ganapp.apis;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 15:14
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanService {
    private final static String BASE_URI = "http://gank.io/api/";

    private GanService() {

    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static Retrofit getRetrofit(String url) {
        if (TextUtils.isEmpty(url)) {
            url = BASE_URI;
        }
        return new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createApi(Class<T> cls, String url) {
        return getRetrofit(url).create(cls);
    }
}
