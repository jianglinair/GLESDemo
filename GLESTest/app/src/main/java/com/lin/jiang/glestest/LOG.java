package com.lin.jiang.glestest;

import android.util.Log;

/**
 * Created by jianglin on 17-4-26.
 */

public class LOG {
    private static final String TAG = "OpenGLES";

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
