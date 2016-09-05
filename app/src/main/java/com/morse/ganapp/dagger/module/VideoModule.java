package com.morse.ganapp.dagger.module;

import com.morse.ganapp.ui.interfaces.IVideoView;

import dagger.Module;
import dagger.Provides;

/**
 * auther：Morse
 * time：2016/9/5 16:47
 * Descripte：
 */
@Module
public class VideoModule {

    private IVideoView iVideoView;

    public VideoModule(IVideoView videoView) {
        iVideoView = videoView;
    }

    @Provides
    IVideoView provideIVideoView() {
        return iVideoView;
    }

}
