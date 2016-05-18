package com.morse.ganapp.apis;

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

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URI)
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private GanService(){

    }

    public static <T>T createApi(Class<T> cls){
        return retrofit.create(cls);
    }
}
