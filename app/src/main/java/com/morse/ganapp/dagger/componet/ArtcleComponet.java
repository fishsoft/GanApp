package com.morse.ganapp.dagger.componet;

import com.morse.ganapp.dagger.module.ArtcleModule;
import com.morse.ganapp.ui.activity.ArtcleActivity;

import dagger.Component;

/**
 * auther：Morse
 * time：2016/9/5 16:46
 * Descripte：
 */
@Component(modules = ArtcleModule.class)
public interface ArtcleComponet {
    public void inject(ArtcleActivity activity);
}
