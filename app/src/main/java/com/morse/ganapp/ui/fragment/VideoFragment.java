package com.morse.ganapp.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morse.ganapp.R;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:03
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class VideoFragment extends BaseFragment {

    public VideoFragment() {
    }

    @Override
    protected View setLayout(ViewGroup container) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_actcle, container, false);
    }

    @Override
    protected void initView() {

    }
}
