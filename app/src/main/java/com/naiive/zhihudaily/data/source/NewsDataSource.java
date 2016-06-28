package com.naiive.zhihudaily.data.source;

import android.support.annotation.NonNull;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.model.News;

import rx.Observable;

/**
 * Created by wangzhe on 16/5/22.
 */
public interface NewsDataSource extends DataSource {

    Observable<News> getNews();

    Observable<News> getMoreNews(String date);

    void cacheNews(@NonNull News news);

    void refreshNews();
}
