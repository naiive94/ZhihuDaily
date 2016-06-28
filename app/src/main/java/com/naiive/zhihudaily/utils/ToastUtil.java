package com.naiive.zhihudaily.utils;

import android.widget.Toast;

import com.naiive.zhihudaily.AppContext;

/**
 * Created by wangzhe on 16/6/21.
 */
public class ToastUtil {

    public static void show(String str) {
        Toast.makeText(AppContext.getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
