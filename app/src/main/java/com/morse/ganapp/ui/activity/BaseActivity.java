package com.morse.ganapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 15:38
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected abstract void setLayout();

    protected void beforeView() {
    }

    ;

    protected abstract void afterView();

    protected void viewClick(View view) {
    }

    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        ButterKnife.inject(this);
        beforeView();
        afterView();
    }

    @Override
    public void onClick(View v) {
        viewClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
