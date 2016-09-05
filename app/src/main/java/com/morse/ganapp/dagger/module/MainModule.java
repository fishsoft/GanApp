package com.morse.ganapp.dagger.module;

import com.morse.ganapp.ui.interfaces.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * auther：Morse
 * time：2016/9/5 16:56
 * Descripte：
 */
@Module
public class MainModule {
    private IMainView iMainView;

    public MainModule(IMainView mainView) {
        iMainView = mainView;
    }

    @Provides
    IMainView provideIMainView() {
        return iMainView;
    }
}
