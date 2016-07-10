package com.naiive.zhihudaily.data.source.remote;

import android.support.annotation.NonNull;

import com.naiive.zhihudaily.common.api.ZhihuApi;
import com.naiive.zhihudaily.common.api.ZhihuFactory;
import com.naiive.zhihudaily.model.News;
import com.naiive.zhihudaily.data.source.NewsDataSource;
import com.naiive.zhihudaily.utils.DateUtil;
import com.naiive.zhihudaily.utils.LocalCacheUtil;

import java.util.Date;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/5/22.
 */
public class NewsRemoteDataSource implements NewsDataSource {


    private ZhihuApi api;

    public NewsRemoteDataSource() {
        api = ZhihuFactory.getApi();
    }


    @Override
    public Observable<News> getNews() {
        return api.getLatestNews().filter(new Func1<News, Boolean>() {
            @Override
            public Boolean call(News news) {
                return news!=null;
            }
        }).doOnNext(new Action1<News>() {
            @Override
            public void call(News news) {
                cacheNews(news, news.getDate());
            }
        });
    }

    @Override
    public Observable<News> getMoreNews(final String beforeDate) {
        return api.getMoreNews(beforeDate).filter(new Func1<News, Boolean>() {
            @Override
            public Boolean call(News news) {
                return news!=null;
            }
        }).doOnNext(new Action1<News>() {
            @Override
            public void call(News news) {
                cacheNews(news, beforeDate);
            }
        });
    }

    @Override
    public void cacheNews(@NonNull News news) {
        cacheNews(news, DateUtil.date2String(new Date()));
    }

    public void cacheNews(@NonNull News news, String beforeDate) {
        String key = LocalCacheUtil.KeyHelper.moreDataKey(beforeDate);
        LocalCacheUtil.getDefault().putData(key, news);
    }

    @Override
    public void refreshNews() {
        //not required
    }
}
