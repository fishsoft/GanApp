package com.morse.ganapp.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.morse.ganapp.ui.fragment.ArtcleFragment;

import java.util.ArrayList;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:38
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTypes;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public GanPagerAdapter(FragmentManager manager, String[] types) {
        super(manager);
        mTypes = types;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", mTypes[position]);
        ArtcleFragment fragment = ArtcleFragment.getInstance();
        fragment.setArguments(bundle);
        mFragments.add(fragment);
        Log.d("GanPagerAdaptel", "getItem:" + position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTypes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTypes[position];
    }
}
