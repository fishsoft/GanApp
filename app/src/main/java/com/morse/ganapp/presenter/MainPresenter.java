package com.morse.ganapp.presenter;

import com.morse.ganapp.ui.interfaces.IMainView;

import javax.inject.Inject;

/**
 * 作者：Morse
 * 创建时间：2016/8/24 14:44
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class MainPresenter extends BasePresenter {

    private IMainView mMainView;

    @Inject
    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
    }

}
