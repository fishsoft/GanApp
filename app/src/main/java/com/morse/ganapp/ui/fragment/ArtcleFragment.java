package com.morse.ganapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morse.ganapp.R;
import com.morse.ganapp.apis.GanApi;
import com.morse.ganapp.apis.GanService;
import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.adapter.ArtcleAdapter;
import com.morse.ganapp.ui.utils.AutoRecyclerView;
import com.morse.ganapp.ui.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
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
public class ArtcleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AutoRecyclerView.loadMoreListener {

    private AutoRecyclerView mArtcleRecy;
    private SwipeRefreshLayout mArtcleSwipe;
    private View view;
    private ArtcleAdapter mAdapter;
    private List<ResultEntity> mResultEntities;
    private String mType;
    private int mPage = 1;

    public static ArtcleFragment getInstance() {
        ArtcleFragment fragment = new ArtcleFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("position");
        Log.d("GanPagerAdapter", "ArtcleFragment beforeView" + mType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_artcle, container, false);
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

    private void initView() {

        mArtcleRecy = (AutoRecyclerView) view.findViewById(R.id.artcle_recy);
        mArtcleSwipe = (SwipeRefreshLayout) view.findViewById(R.id.artcle_swipe);

        mArtcleSwipe.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mArtcleRecy.setLayoutManager(layoutManager);
        mArtcleRecy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration
                .VERTICAL_LIST));
        mArtcleRecy.setLoadMoreListener(this);

        Log.d("GanPagerAdapter", "ArtcleFragment" + mType);
        initData();
        loadData();
    }

    private void initData() {
        mResultEntities = new ArrayList<ResultEntity>();
        mAdapter = new ArtcleAdapter(getActivity(), mResultEntities);
        mArtcleRecy.setAdapter(mAdapter);
    }

    private void loadData() {
        Log.d("GanPagerAdapter", "ArtcleFragment loadData");
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

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ResultEntity> resultEntities) {
                        Log.d("GanPagerAdapter", "ArtcleFragment" + resultEntities.toString());
                        mResultEntities.addAll(resultEntities);
                        mAdapter.notifyDataSetChanged();
                        if (resultEntities.size() >= 10) {
                            mPage++;
                        }
                    }
                });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
