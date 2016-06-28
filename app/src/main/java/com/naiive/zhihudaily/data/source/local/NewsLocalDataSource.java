package com.naiive.zhihudaily.data.source.local;

import android.support.annotation.NonNull;

import com.naiive.zhihudaily.model.News;
import com.naiive.zhihudaily.data.source.NewsDataSource;
import com.naiive.zhihudaily.utils.DateUtil;
import com.naiive.zhihudaily.utils.LocalCacheUtil;

import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/5/22.
 */
public class NewsLocalDataSource implements NewsDataSource {


    @Override
    public Observable<News> getNews() {
        return getNews(DateUtil.date2String(new Date()));
    }

    @Override
    public Observable<News> getMoreNews(String beforeDate) {
        return getNews(beforeDate);
    }

    private Observable<News> getNews(final String beforeDate){
        final String key = LocalCacheUtil.KeyHelper.moreDataKey(beforeDate);
        return Observable.create(new Observable.OnSubscribe<News>() {
            @Override
            public void call(Subscriber<? super News> subscriber) {
                News news = LocalCacheUtil.getDefault().fetchData(key, News.class);
                subscriber.onNext(news);
                subscriber.onCompleted();
            }
        })
                .filter(new Func1<News, Boolean>() {
                    @Override
                    public Boolean call(News news) {
                        return news != null;
                    }
                });
    }

    @Override
    public void cacheNews(@NonNull News news) {
        //not required
    }



    @Override
    public void refreshNews() {
        //not required
    }
}
