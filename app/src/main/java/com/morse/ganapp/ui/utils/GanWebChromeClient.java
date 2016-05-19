package com.morse.ganapp.ui.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 16:40
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanWebChromeClient extends WebChromeClient {

    private Activity mActivity;

    public GanWebChromeClient(Activity activity) {
        super();
        mActivity = activity;
    }

    /**
     * 设置进度条
     *
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mActivity.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
        super.onProgressChanged(view, newProgress);
    }

    /**
     * 设置标题
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        mActivity.setTitle(title);
        super.onReceivedTitle(view, title);
    }

    /**
     * 处理js对话框
     *
     * @param view
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                result.confirm();
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
        return true;
    }
}
