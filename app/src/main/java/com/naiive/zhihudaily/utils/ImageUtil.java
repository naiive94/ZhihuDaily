package com.naiive.zhihudaily.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.naiive.zhihudaily.AppContext;

/**
 * Created by wangzhe on 16/6/24.
 */
public class ImageUtil {

    public static void load(String url, ImageView imageView) {

        Glide.with(AppContext.getContext()).load(url).into(imageView);
    }
}
