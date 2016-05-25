package com.morse.ganapp.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.morse.ganapp.R;
import com.morse.ganapp.apis.GanApi;
import com.morse.ganapp.apis.GanService;
import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.utils.GanWebChromeClient;
import com.morse.ganapp.ui.utils.GanWebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 16:23
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleActivity extends BaseActivity {

    @InjectView(R.id.artcle_backdrop)
    ImageView mArtcleBackdrop;
    @InjectView(R.id.artcle_toolbar)
    Toolbar mArtcleToolbar;
    @InjectView(R.id.artcle_collapsing)
    CollapsingToolbarLayout mArtcleCollapsing;
    @InjectView(R.id.artcle_appbar)
    AppBarLayout mArtcleAppbar;
    @InjectView(R.id.webview)
    WebView mWebview;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_artcle);
        ButterKnife.inject(this);
    }

    @Override
    protected void afterView() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            Log.d("GanPagerAdapter", "ArtcleFragment loadData" + title);
            mArtcleToolbar.setTitle(title);
        }
        setSupportActionBar(mArtcleToolbar);


        GanService.createApi(GanApi.class, null)
                .getGan("福利", 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GanBean, ResultEntity>() {
                    @Override
                    public ResultEntity call(GanBean ganBean) {
                        return ganBean.getResults().get(0);
                    }
                })
                .map(new Func1<ResultEntity, BitmapTypeRequest<String>>() {
                    @Override
                    public BitmapTypeRequest<String> call(ResultEntity resultEntity) {
                        return Glide.with(ArtcleActivity.this).load(resultEntity.getUrl()).asBitmap();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BitmapTypeRequest<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BitmapTypeRequest<String> stringBitmapTypeRequest) {
                        stringBitmapTypeRequest.into(mArtcleBackdrop);
                        colorChange();
                    }
                });

        mUrl = getIntent().getStringExtra("url");

        initWebView();

        mWebview.loadUrl(mUrl);

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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    private void colorChange() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mArtcleBackdrop.getId());
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
            /* 界面颜色UI统一性处理,看起来更Material一些 */
                mArtcleAppbar.setBackgroundColor(vibrant.getRgb());
//                mArtcleAppbar.setTextColor(vibrant.getTitleTextColor());
                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明

                mArtcleToolbar.setBackgroundColor(vibrant.getRgb());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
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
