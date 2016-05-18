package com.morse.ganapp.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.morse.ganapp.R;
import com.morse.ganapp.ui.fragment.ArtcleFragment;
import com.morse.ganapp.ui.fragment.VideoFragment;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:38
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTypes = null;

    public GanPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mTypes = context.getResources().getStringArray(R.array.ganType);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                fragment=new ArtcleFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("position",position);
                fragment.setArguments(bundle);
                break;
            case 5:
                fragment=new VideoFragment();
        }
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
