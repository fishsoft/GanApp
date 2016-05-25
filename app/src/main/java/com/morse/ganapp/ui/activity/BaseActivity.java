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

    protected String mUrl;

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

    /**
     * 初始化WebView设置
     */
//    protected void initWebView(Activity activity, WebView webView) {
//        WebSettings settings = webView.getSettings();
//        //自适应屏幕
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
//        //加载js
//        settings.setJavaScriptEnabled(true);
//        //缓存
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        //自动加载图片
//        settings.setLoadsImagesAutomatically(true);
//        // 设置显示缩放按钮
//        settings.setBuiltInZoomControls(true);
//        //支持手动缩放
//        settings.setSupportZoom(true);
//        //允许访问文件
//        settings.setAllowFileAccess(true);
//        //显示图片
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        //设置WebViewClient
//        if (!TextUtils.isEmpty(mUrl)) {
//            webView.setWebViewClient(new GanWebViewClient(activity, mUrl));
//        }
//        //设置WebChromeClient
//        webView.setWebChromeClient(new GanWebChromeClient(activity));
//    }
}
