package com.morse.ganapp.dagger.module;

import com.morse.ganapp.ui.interfaces.IArtcleView;

import dagger.Module;
import dagger.Provides;

/**
 * auther：Morse
 * time：2016/9/5 16:46
 * Descripte：
 */
@Module
public class ArtcleModule {

    private IArtcleView iArtcleView;

    public ArtcleModule(IArtcleView artcleView) {
        iArtcleView = artcleView;
    }

    @Provides
    IArtcleView provideIArtcleView() {
        return iArtcleView;
    }
}
