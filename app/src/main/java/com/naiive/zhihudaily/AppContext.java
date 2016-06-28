package com.naiive.zhihudaily;

import android.app.Application;
import android.content.Context;

import com.naiive.zhihudaily.utils.LogUtil;

/**
 * Created by wangzhe on 16/5/22.
 */
public class AppContext  extends Application{

    private static AppContext INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        LogUtil.DEBUG = true;
    }

    public static Context getContext(){
        return INSTANCE.getApplicationContext();
    }


}
