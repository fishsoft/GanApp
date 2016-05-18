package com.morse.ganapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:05
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View view;

    protected abstract View setLayout();

    protected void beforeView() {
    }

    ;

    protected abstract void initView();

    protected void viewClick(View view) {
    }

    ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == container) {
            view = setLayout();
            beforeView();
            initView();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        viewClick(v);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
