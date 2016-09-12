package com.morse.ganapp.ui.activity;

import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.morse.ganapp.R;
import com.morse.ganapp.dagger.componet.DaggerArtcleComponet;
import com.morse.ganapp.dagger.module.ArtcleModule;
import com.morse.ganapp.presenter.ArtclePresenter;
import com.morse.ganapp.ui.interfaces.IArtcleView;
import com.morse.ganapp.ui.utils.GanWebChromeClient;
import com.morse.ganapp.ui.utils.GanWebViewClient;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:23
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleActivity extends BaseActivity implements IArtcleView {

    @BindView(R.id.artcle_backdrop)
    ImageView mArtcleBackdrop;
    @BindView(R.id.artcle_toolbar)
    Toolbar mArtcleToolbar;
    @BindView(R.id.artcle_collapsing)
    CollapsingToolbarLayout mArtcleCollapsing;
    @BindView(R.id.artcle_appbar)
    AppBarLayout mArtcleAppbar;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.tv_net_error)
    TextView mTvNetError;

    @Inject
    ArtclePresenter presenter;

    private String mUrl;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_artcle);
    }

    @Override
    protected void beforeView() {
        super.beforeView();
        mUrl = getIntent().getStringExtra("url");
    }

    @Override
    protected void afterView() {

        initSystemBar(this);

        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            mArtcleToolbar.setTitle(title);
        }
        setSupportActionBar(mArtcleToolbar);

        DaggerArtcleComponet.builder().artcleModule(new ArtcleModule(this)).build().inject(this);

        presenter.getImgUrl();

        if (!TextUtils.isEmpty(mUrl)) {
            if (mTvNetError.getVisibility() != View.VISIBLE) {
                mTvNetError.setVisibility(View.VISIBLE);
            }
            initWebView();
            mWebview.loadUrl(mUrl);
        } else {
            if (mTvNetError.getVisibility() != View.GONE) {
                mTvNetError.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void tryRequest() {
        super.tryRequest();
    }

    /**
     * 初始化WebView设置
     */
    protected void initWebView() {
        WebSettings settings = mWebview.getSettings();
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
        mWebview.setWebViewClient(new GanWebViewClient(this));
        //设置WebChromeClient
        mWebview.setWebChromeClient(new GanWebChromeClient(this));

    }

    @Override
    public void onSuccess(String imgUrl) {
        Glide.with(ArtcleActivity.this).load(imgUrl).asBitmap().into(mArtcleBackdrop);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "加载数据失败", Toast.LENGTH_SHORT).show();
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // 处理加载进度条
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null)
                view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

}
