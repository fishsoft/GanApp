package com.morse.ganapp.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.morse.ganapp.ui.fragment.ArtcleFragment;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:38
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTypes = new String[]{"Android","iOS","前端","瞎推荐","推展资源","休息视频"};

    public GanPagerAdapter(FragmentManager manager,String[] types) {
        super(manager);
        mTypes = types;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", mTypes[position]);
//        switch (position) {
//            case 0:
//            case 1:
//            case 2:
//            case 3:
//            case 4:
        ArtcleFragment fragment = new ArtcleFragment();
//                break;
//            case 5:
//                fragment = VideoFragment.getInstance();
//                break;
//            default:
//                break;
//        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTypes[position];
    }
}
