package com.morse.ganapp.dagger.componet;

import com.morse.ganapp.dagger.module.DailyModule;
import com.morse.ganapp.ui.activity.DailyActivity;

import dagger.Component;

/**
 * auther：Morse
 * time：2016/9/5 16:44
 * Descripte：
 */
@Component(modules = DailyModule.class)
public interface DailyComponet {
    public void inject(DailyActivity activity);
}
