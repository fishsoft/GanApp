package com.morse.ganapp.presenter;

import com.morse.ganapp.http.HttpMethod;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.subscribe.GanSubscribe;
import com.morse.ganapp.ui.interfaces.IArtcleView;

import java.util.List;

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

    public void getImgUrl(){
        HttpMethod.getInstance().getGan(new GanSubscribe<List<ResultEntity>>(new GanSubscribe.GankNext() {
            @Override
            public void onNext(Object o) {
                List<ResultEntity> entities = (List<ResultEntity>) o;
                iArtcleView.onSuccess(entities.get(0).getUrl());
            }

            @Override
            public void onError(Throwable e) {
                try {
                    e.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                iArtcleView.onFailure();
            }
        }), "福利", 1);
    }

}
