package com.morse.ganapp.ui.utils;

import android.util.Log;

/**
 * 作者：Morse
 * 创建时间：2016/8/25 17:14
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class LogUtils {

    private final static String TAG = "gan.io";
    private static boolean isDebug = true;

    public static void d(String text) {
        if (isDebug) {
            Log.d(TAG, text);
        }
    }

    public static void e(String text) {
        if (isDebug) {
            Log.e(TAG, text);
        }
    }

    public static void v(String text) {
        if (isDebug) {
            Log.v(TAG, text);
        }
    }

    public static void w(String text) {
        if (isDebug) {
            Log.w(TAG, text);
        }
    }

}
