package com.morse.ganapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morse.ganapp.R;
import com.morse.ganapp.http.HttpMethod;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.subscribe.GanSubscribe;
import com.morse.ganapp.ui.adapter.WelfareAdapter;
import com.morse.ganapp.ui.interfaces.EndLessOnScrollListener;
import com.morse.ganapp.ui.weight.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Morse
 * 创建时间：2016/6/14 17:43
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WelfareFragmnet extends BaseFragment implements GanSubscribe.GankNext, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.welfare_recy)
    RecyclerView mWelfareRecy;
    @BindView(R.id.welfare_swipe)
    SwipeRefreshLayout mWelfareSwipe;
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

    @Override
    protected View setLayout(ViewGroup container) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_welfare, container, false);
        return view;
    }

    protected void initView() {
        mWelfareSwipe.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mWelfareSwipe.setOnRefreshListener(this);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mWelfareRecy.setLayoutManager(gridLayoutManager);
        mWelfareRecy.addItemDecoration(new SpacesItemDecoration(10));
        mWelfareRecy.addOnScrollListener(new EndLessOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mWelfareSwipe.setRefreshing(true);
                loadData(currentPage);
            }
        });
        mResultEntities = new ArrayList<>();
        mAdapter = new WelfareAdapter(getActivity(), mResultEntities);
        mWelfareRecy.setAdapter(mAdapter);
        loadData(mPage);
    }

    private void loadData(int currentPage) {
        HttpMethod.getInstance().getGan(new GanSubscribe<List<ResultEntity>>(this), mType, 10, currentPage);
    }

    @Override
    public void onRefresh() {
        if (mWelfareSwipe.isRefreshing()) {
            mWelfareSwipe.setRefreshing(false);
        }
        mWelfareSwipe.setRefreshing(true);
        mPage = 1;
        loadData(mPage);
    }

    @Override
    public void onNext(Object o) {
        if (mWelfareSwipe.isRefreshing()) {
            mWelfareSwipe.setRefreshing(false);
        }
        List<ResultEntity> resultEntities = (List<ResultEntity>) o;
        if (1 == mPage) {
            mResultEntities.clear();
        }
        mResultEntities.addAll(resultEntities);
        mAdapter.notifyDataSetChanged();
        if (resultEntities.size() == 10) {
            mPage += 1;
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mWelfareSwipe.isRefreshing()) {
            mWelfareSwipe.setRefreshing(false);
        }
        e.printStackTrace();
    }
}
