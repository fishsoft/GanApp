package com.morse.ganapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morse.ganapp.R;
import com.morse.ganapp.http.HttpMethod;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.subscribe.GanSubscribe;
import com.morse.ganapp.ui.adapter.ArtcleAdapter;
import com.morse.ganapp.ui.utils.AutoRecyclerView;
import com.morse.ganapp.ui.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:02
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleFragment extends BaseFragment implements GanSubscribe.GankNext, SwipeRefreshLayout.OnRefreshListener, AutoRecyclerView.loadMoreListener {

    @BindView(R.id.artcle_recy)
    AutoRecyclerView mArtcleRecy;
    @BindView(R.id.artcle_swipe)
    SwipeRefreshLayout mArtcleSwipe;
    private View view;
    private ArtcleAdapter mAdapter;
    private List<ResultEntity> mResultEntities;
    private String mType;
    private int mPage = 1;
    private Unbinder unbinder;

    public static ArtcleFragment getInstance() {
        ArtcleFragment fragment = new ArtcleFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("position");
    }

    @Override
    protected View setLayout(ViewGroup container) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_artcle, container, false);
        return view;
    }

    protected void initView() {
        mArtcleSwipe.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mArtcleRecy.setLayoutManager(layoutManager);
        mArtcleRecy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration
                .VERTICAL_LIST));
        mArtcleRecy.setLoadMoreListener(this);

        initData();
        loadData();
    }

    private void initData() {
        mResultEntities = new ArrayList<ResultEntity>();
        mAdapter = new ArtcleAdapter(getActivity(), mResultEntities);
        mArtcleRecy.setAdapter(mAdapter);
    }

    private void loadData() {
        Log.d("loadData", "mPage:" + mPage);
        HttpMethod.getInstance().getGan(new GanSubscribe<List<ResultEntity>>(this), mType, 10, mPage);
    }

    @Override
    public void onRefresh() {
        mArtcleSwipe.setRefreshing(true);
        mPage = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        mArtcleSwipe.setRefreshing(true);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onNext(Object o) {
        mArtcleSwipe.setRefreshing(false);
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
        mArtcleSwipe.setRefreshing(false);
        e.printStackTrace();
    }
}
