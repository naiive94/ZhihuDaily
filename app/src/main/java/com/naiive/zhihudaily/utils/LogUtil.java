package com.naiive.zhihudaily.utils;

import android.util.Log;

/**
 * Created by wangzhe on 16/6/2.
 */
public class LogUtil {

    public static boolean DEBUG = true;
    private static final String TAG = "Logger";

    public static void info(String string){
        if (DEBUG){
            Log.d(TAG,string);
        }
    }

}
