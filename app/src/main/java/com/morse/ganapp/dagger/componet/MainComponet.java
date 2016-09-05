package com.morse.ganapp.dagger.componet;

import com.morse.ganapp.dagger.module.MainModule;
import com.morse.ganapp.ui.activity.MainActivity;

import dagger.Component;

/**
 * auther：Morse
 * time：2016/9/5 16:56
 * Descripte：
 */
@Component(modules = MainModule.class)
public interface MainComponet {
    public void inject(MainActivity activity);
}
