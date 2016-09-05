package com.morse.ganapp.dagger.module;

import com.morse.ganapp.ui.interfaces.IDailyView;

import dagger.Module;
import dagger.Provides;

/**
 * auther：Morse
 * time：2016/9/5 16:44
 * Descripte：
 */
@Module
public class DailyModule {

    private IDailyView iDailyView;

    public DailyModule(IDailyView dailyView) {
        iDailyView = dailyView;
    }

    @Provides
    IDailyView provideIDailyView() {
        return iDailyView;
    }

}
