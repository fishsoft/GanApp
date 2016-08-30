package com.morse.ganapp.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.morse.ganapp.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 15:38
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected String mUrl;

    protected abstract void setLayout();

    protected void beforeView() {
    }

    protected abstract void afterView();

    protected void tryRequest(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        ButterKnife.bind(this);
        beforeView();
        afterView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(19)
    private void setTranslucentStatus(Activity activity, boolean on) {
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(0);//状态栏无背景
        } else {
            winParams.flags &= ~bits;
            tintManager.setStatusBarTintEnabled(false);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//状态栏无背景
        }
        win.setAttributes(winParams);
    }


    public void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_net_error:
                tryRequest();
                break;
        }
    }
}
