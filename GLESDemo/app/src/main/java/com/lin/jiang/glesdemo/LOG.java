package com.lin.jiang.glesdemo;

import android.util.Log;

/**
 * Created by jianglin on 17-5-4.
 */

public class LOG {

    private static final String TAG = "OpenGLESDemo";

    public static void i(String tag, String msg) {
        Log.i(TAG, "[" + tag + "]" + msg);
    }

    public static void d(String tag, String msg) {
        Log.d(TAG, "[" + tag + "]" + msg);
    }

    public static void e(String tag, String msg) {
        Log.e(TAG, "[" + tag + "]" + msg);
    }
}
