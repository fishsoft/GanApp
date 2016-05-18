package com.morse.ganapp.ui.fragment;

import android.view.View;

import com.morse.ganapp.apis.GanApi;
import com.morse.ganapp.apis.GanService;
import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultEntity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:02
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleFragment extends BaseFragment {

    private String mType;

    @Override
    protected View setLayout() {
        return null;
    }

    @Override
    protected void beforeView() {
        mType = (String) getArguments().get("position");
    }

    @Override
    protected void initView() {

    }

    private void loadData() {
        GanService.createApi(GanApi.class)
                .getGan(mType, 10, 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GanBean,List<ResultEntity>>() {
                    @Override
                    public List<ResultEntity> call(GanBean ganBean) {
                        return ganBean.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ResultEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ResultEntity> resultEntities) {

                    }
                });
    }
}
