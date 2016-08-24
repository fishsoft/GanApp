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
import com.morse.ganapp.http.HttpMethod;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.subscribe.GanSubscribe;
import com.morse.ganapp.ui.adapter.WelfareAdapter;
import com.morse.ganapp.ui.utils.AutoRecyclerView;
import com.morse.ganapp.ui.weight.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Morse
 * 创建时间：2016/6/14 17:43
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WelfareFragmnet extends Fragment implements GanSubscribe.GankNext, SwipeRefreshLayout.OnRefreshListener, AutoRecyclerView.loadMoreListener {

    @BindView(R.id.welfare_recy)
    AutoRecyclerView mWelfareRecy;
    @BindView(R.id.welfare_swipe)
    SwipeRefreshLayout mWelfareSwipe;
    private View view;

    private List<ResultEntity> mResultEntities;
    private WelfareAdapter mAdapter;
    private String mType;
    private int mPage = 1;
    private Unbinder unbinder;

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
            unbinder = ButterKnife.bind(this, view);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }
        return view;
    }

    protected void initView() {
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
        HttpMethod.getInstance().getGan(new GanSubscribe<List<ResultEntity>>(this), mType, 10, mPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mWelfareSwipe.setRefreshing(true);
        mPage = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        mWelfareSwipe.setRefreshing(true);
        loadData();
    }

    @Override
    public void onNext(Object o) {
        mWelfareSwipe.setRefreshing(false);
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
        mWelfareSwipe.setRefreshing(false);
        e.printStackTrace();
    }
}
