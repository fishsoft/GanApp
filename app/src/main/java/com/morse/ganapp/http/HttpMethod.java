package com.morse.ganapp.http;

import com.google.gson.Gson;
import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultDay;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.utils.LogUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：Morse
 * 创建时间：2016/8/24 15:10
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class HttpMethod {
    private final static String BASE_URI = "http://gank.io/api/";
    private final static int DEFAULT_TIMEOUT = 30 * 1000;
    private Retrofit retrofit;
    private GanApi mGanApi;

    private HttpMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .baseUrl(BASE_URI)
                .build();
        mGanApi = retrofit.create(GanApi.class);
    }

    public static HttpMethod getInstance() {
        return Singleton.instance;
    }

    public void getGan(Subscriber<List<ResultEntity>> subscriber, String type, int num) {
        Observable observable = mGanApi.getGan(type, num).map(new HttpResultFunc<List<ResultEntity>>());

        toSubscribe(observable, subscriber);
    }

    public void getGan(Subscriber<List<ResultEntity>> subscriber, String type, int num, int page) {
        Observable observable = mGanApi.getGan(type, num, page).map(new HttpResultFunc<List<ResultEntity>>());

        toSubscribe(observable, subscriber);
    }

    public void getDailyGan(Subscriber<List<ResultDay>> subscriber, int year, int mouth, int day) {
        Observable observable = mGanApi.getDailyGan(year, mouth, day).map(new HttpResultFunc<List<ResultDay>>());

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    private static class Singleton {
        private static final HttpMethod instance = new HttpMethod();
    }

    private class HttpResultFunc<T> implements Func1<GanBean<T>, T> {

        @Override
        public T call(GanBean<T> tGanBean) {
            LogUtils.d("请求结果：" + tGanBean.toString());
            if (tGanBean.isError()) {
                LogUtils.d("获取数据失败");
                return null;
            }
            return tGanBean.getResults();
        }
    }

}
