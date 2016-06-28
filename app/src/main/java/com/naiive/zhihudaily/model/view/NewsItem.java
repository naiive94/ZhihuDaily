package com.naiive.zhihudaily.model.view;

/**
 * Created by wangzhe on 16/6/24.
 */
public class NewsItem {

    public String title;
    public String firstImage;
    public int isMultiImages;

    public NewsItem(String title, String firstImage, int isMultiImages) {
        this.title = title;
        this.firstImage = firstImage;
        this.isMultiImages = isMultiImages;
    }
}
