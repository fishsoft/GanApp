package com.morse.ganapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:05
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public abstract class BaseFragment extends Fragment {

    protected View view;
    private Unbinder unbinder;

    protected abstract View setLayout(ViewGroup container);

    protected void beforeView() {
    }

    protected abstract void initView();

    protected void afterView() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = setLayout(container);
        unbinder = ButterKnife.bind(this,view);
        beforeView();
        initView();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
