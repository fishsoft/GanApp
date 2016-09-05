package com.morse.ganapp.presenter;

import com.morse.ganapp.ui.interfaces.IDailyView;

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
}
