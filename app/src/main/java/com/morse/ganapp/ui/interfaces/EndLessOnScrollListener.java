package com.morse.ganapp.ui.interfaces;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * RecyclerView自动加载
 * Created by wnw on 16-5-26.
 */
public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    //声明一个LinearLayoutManager
    private RecyclerView.LayoutManager mLayoutManager;

    //当前页，从0开始
    private int currentPage = 0;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;

    public EndLessOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = getFirstVisiblePosition();

        if (loading) {

            if (totalItemCount > previousTotal) {
                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        //这里需要好好理解
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    private int getFirstVisiblePosition() {
        int position = 0;
        if (mLayoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        } else if (mLayoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPositions(lastPositions);
        } else {
            position = 0;
        }
        return position;
    }

    /**
     * 获得当前展示最小的position
     *
     * @param positions
     * @return
     */
    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getLastVisiblePosition() {
        int position;
        if (mLayoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mLayoutManager;
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = mLayoutManager.getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    public abstract void onLoadMore(int currentPage);
}
