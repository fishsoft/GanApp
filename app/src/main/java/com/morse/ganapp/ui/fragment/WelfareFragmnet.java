package com.morse.ganapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morse.ganapp.R;
import com.morse.ganapp.apis.GanApi;
import com.morse.ganapp.apis.GanService;
import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.adapter.WelfareAdapter;
import com.morse.ganapp.ui.utils.AutoRecyclerView;
import com.morse.ganapp.ui.weight.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：Morse
 * 创建时间：2016/6/14 17:43
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WelfareFragmnet extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AutoRecyclerView.loadMoreListener {

    private AutoRecyclerView mWelfareRecy;
    private SwipeRefreshLayout mWelfareSwipe;
    private View view;

    private List<ResultEntity> mResultEntities;
    private WelfareAdapter mAdapter;
    private String mType;
    private int mPage = 1;

    public static WelfareFragmnet getInstance() {
        WelfareFragmnet fragment = new WelfareFragmnet();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_welfare, container, false);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }
        ButterKnife.inject(this, view);
        return view;
    }

    protected void initView() {
        mWelfareRecy = (AutoRecyclerView) view.findViewById(R.id.welfare_recy);
        mWelfareSwipe = (SwipeRefreshLayout) view.findViewById(R.id.welfare_swipe);
        mWelfareSwipe.setOnRefreshListener(this);
        mWelfareRecy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mWelfareRecy.addItemDecoration(new SpacesItemDecoration(10));
        mWelfareRecy.setLoadMoreListener(this);
        mResultEntities = new ArrayList<>();
        mAdapter = new WelfareAdapter(getActivity(), mResultEntities);
        mWelfareRecy.setAdapter(mAdapter);
        loadData();
    }

    private void loadData() {
        Log.d("loadData", "mPage:" + mPage);
        GanService.createApi(GanApi.class, null)
                .getGan(mType, 10, mPage)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GanBean, List<ResultEntity>>() {
                    @Override
                    public List<ResultEntity> call(GanBean ganBean) {
                        return ganBean.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ResultEntity>>() {
                    @Override
                    public void onCompleted() {
                        mWelfareSwipe.setRefreshing(false);
                        mWelfareRecy.setLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mWelfareSwipe.setRefreshing(false);
                        mWelfareRecy.setLoading(false);
                    }

                    @Override
                    public void onNext(List<ResultEntity> resultEntities) {
                        if (1 == mPage) {
                            mResultEntities.clear();
                        }
                        mResultEntities.addAll(resultEntities);
                        mAdapter.notifyDataSetChanged();
                        if (resultEntities.size() == 10) {
                            mPage += 1;
                        }
                        Log.d("loadData", "size:" + resultEntities.size());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        loadData();
    }
}
