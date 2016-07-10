package com.naiive.zhihudaily.data.source;

import android.support.annotation.NonNull;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.data.source.local.NewsLocalDataSource;
import com.naiive.zhihudaily.data.source.remote.NewsRemoteDataSource;
import com.naiive.zhihudaily.model.News;
import com.naiive.zhihudaily.model.Story;
import com.naiive.zhihudaily.model.TopStory;
import com.naiive.zhihudaily.model.view.DrawerItem;
import com.naiive.zhihudaily.model.view.NewsItem;
import com.naiive.zhihudaily.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/5/22.
 */
public class NewsRepository implements DataSource {

    private final NewsDataSource mRemoteDataSource;
    private final NewsDataSource mLocalDataSource;

    private News mCachedNews;
    private boolean mCacheIsDirty = false;

    public NewsRepository() {
        mRemoteDataSource = new NewsRemoteDataSource();
        mLocalDataSource = new NewsLocalDataSource();
    }

    public Observable<News> getNews() {
        Observable<News> ret;

        if (mCachedNews != null && !mCacheIsDirty) {
            return Observable.just(mCachedNews);
        }

        final Observable<News> localNews = mLocalDataSource.getNews();

        Observable<News> remoteNews = mRemoteDataSource
                .getNews()
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mCacheIsDirty = false;
                    }
                });

        if (mCacheIsDirty) {
            ret = remoteNews;
        } else {
            ret = Observable.concat(localNews, remoteNews).first();
        }
        return ret.doOnNext(new Action1<News>() {
            @Override
            public void call(News news) {
                mCachedNews = news;
            }
        });
    }


    public Observable<List<NewsItem>> getMoreNews() {
        if (mCachedNews == null) return Observable.empty();

        String date = mCachedNews.getDate();
        Observable<News> remote = mRemoteDataSource.getMoreNews(date);
        Observable<News> local = mLocalDataSource.getMoreNews(date);

        return Observable.concat(remote, local).first().doOnNext(new Action1<News>() {
            @Override
            public void call(News news) {
                mCachedNews.getStories().addAll(news.getStories());
                mCachedNews.setDate(news.getDate());
            }
        }).map(new Func1<News, List<NewsItem>>() {
            @Override
            public List<NewsItem> call(News news) {
                return convertStories(news);
            }
        });
    }

    public void refreshNews() {
        mCacheIsDirty = true;
    }

    public List<NewsItem> convertStories(News news) {
        List<NewsItem> list = new ArrayList<>();
        for (Story story : news.getStories()) {
            list.add(new NewsItem(story.getTitle(), story.getImages().get(0), story.getImages().size() == 1 ? 0 : 1,story.getId()));
        }
        return list;
    }

    public List<String[]> convertTopStories(News news) {
        List<String[]> list = new ArrayList<>();
        int size = news.getTopStories().size();
        String[] images = new String[size];
        String[] titles = new String[size];
        int i = 0;
        for (TopStory story : news.getTopStories()) {
            images[i] = story.getImage();
            titles[i] = story.getTitle();
            i++;
        }
        list.add(images);
        list.add(titles);
        return list;

    }

}
