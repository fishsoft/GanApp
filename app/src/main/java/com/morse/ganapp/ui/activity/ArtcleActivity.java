package com.morse.ganapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.morse.ganapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:23
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleActivity extends AppCompatActivity {

    @InjectView(R.id.artcle_backdrop)
    ImageView mArtcleBackdrop;
    @InjectView(R.id.artcle_toolbar)
    Toolbar mArtcleToolbar;
    @InjectView(R.id.artcle_collapsing)
    CollapsingToolbarLayout mArtcleCollapsing;
    @InjectView(R.id.artcle_appbar)
    AppBarLayout mArtcleAppbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artcle);

        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
