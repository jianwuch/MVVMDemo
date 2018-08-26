package com.jove.mvvmdemo.util;

import android.util.Log;

/**
 * author : jove.chen
 * e-mail : jianwuch@qq.com
 * date   : 18-8-25上午11:45
 * desc   :
 * version: 1.0
 */
public class AppLog {
    private static final String TAG_PREFIX = "cjw";

    public static void d(String tag, String msg) {
        Log.d(TAG_PREFIX + tag, msg);
    }
}
