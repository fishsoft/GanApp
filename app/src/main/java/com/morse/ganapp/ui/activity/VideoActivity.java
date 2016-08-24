package com.morse.ganapp.ui.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.morse.ganapp.R;
import com.morse.ganapp.ui.utils.GanWebChromeClient;
import com.morse.ganapp.ui.utils.GanWebViewClient;

import butterknife.BindView;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 15:37
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_web)
    WebView mVideoWeb;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void afterView() {
        initWebView();
        mVideoWeb.loadUrl(getIntent().getStringExtra("url"));
    }

    /**
     * 初始化WebView设置
     */
    private void initWebView() {
        WebSettings settings = mVideoWeb.getSettings();
        //自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //加载js
        settings.setJavaScriptEnabled(true);
        //缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 设置显示缩放按钮
        settings.setBuiltInZoomControls(true);
        //支持手动缩放
        settings.setSupportZoom(true);
        //允许访问文件
        settings.setAllowFileAccess(true);
        //显示图片
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置WebViewClient
        mVideoWeb.setWebViewClient(new GanWebViewClient(VideoActivity.this));
        //设置WebChromeClient
        mVideoWeb.setWebChromeClient(new GanWebChromeClient(VideoActivity.this));
    }
}
