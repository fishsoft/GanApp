package com.morse.ganapp.presenter;

import com.morse.ganapp.ui.interfaces.IVideoView;

import javax.inject.Inject;

/**
 * 作者：Morse
 * 创建时间：2016/8/24 14:48
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class VideoPresenter extends BasePresenter {

    private IVideoView mVideoView;

    @Inject
    public VideoPresenter(IVideoView videoView) {
        mVideoView = videoView;
    }

}
