package com.morse.ganapp.subscribe;

import rx.Subscriber;

/**
 * 作者：Morse
 * 创建时间：2016/8/24 16:04
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanSubscribe<T> extends Subscriber<T> {

    private GankNext iGankNext;

    public GanSubscribe(GankNext gankNext) {
        iGankNext = gankNext;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onError(e);
    }

    @Override
    public void onNext(T t) {
        if (null != iGankNext) {
            iGankNext.onNext(t);
        }
    }

    public interface GankNext<T> {
        void onNext(T t);

        void onError(Throwable e);
    }
}
