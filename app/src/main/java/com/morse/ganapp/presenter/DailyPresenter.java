package com.morse.ganapp.presenter;

import com.morse.ganapp.http.HttpMethod;
import com.morse.ganapp.model.ResultDay;
import com.morse.ganapp.subscribe.GanSubscribe;
import com.morse.ganapp.ui.interfaces.IDailyView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * auther：Morse
 * time：2016/9/5 16:59
 * Descripte：
 */
public class DailyPresenter extends BasePresenter {

    private IDailyView mDailyView;

    @Inject
    public DailyPresenter(IDailyView dailyView) {
        mDailyView = dailyView;
    }

    public void getDaily(int year, int mouth, int day) {
        HttpMethod.getInstance().getDailyGan(new GanSubscribe<List<ResultDay>>(new GanSubscribe.GankNext() {
            @Override
            public void onNext(Object o) {
                ArrayList<ResultDay> days = (ArrayList<ResultDay>) o;
                mDailyView.onSuccess(days.get(0).getContent());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mDailyView.onFailure();
            }
        }), year, mouth, day);
    }
}
