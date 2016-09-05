package com.morse.ganapp.presenter;

import com.morse.ganapp.ui.interfaces.IArtcleView;

import javax.inject.Inject;

/**
 * 作者：Morse
 * 创建时间：2016/8/24 14:45
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtclePresenter extends BasePresenter {

    private IArtcleView iArtcleView;

    @Inject
    public ArtclePresenter(IArtcleView artcleView){
        iArtcleView=artcleView;
    }

}
