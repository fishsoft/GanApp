package com.morse.ganapp.dagger.componet;

import com.morse.ganapp.dagger.module.VideoModule;
import com.morse.ganapp.ui.activity.VideoActivity;

import dagger.Component;

/**
 * auther：Morse
 * time：2016/9/5 16:48
 * Descripte：
 */
@Component(modules = VideoModule.class)
public interface VideoComponet {
    public void inject(VideoActivity activity);
}
