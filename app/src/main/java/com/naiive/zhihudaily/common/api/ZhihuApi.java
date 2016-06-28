package com.naiive.zhihudaily.common.api;

import com.naiive.zhihudaily.model.News;
import com.naiive.zhihudaily.model.Topic;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wangzhe on 16/5/22.
 */
public interface ZhihuApi {

    @GET("news/latest")
    Observable<News> getLatestNews();

    @GET("news/before/{date}")
    Observable<News> getMoreNews(@Path("date")String beforeDate);

    @GET("themes")
    Observable<Topic> getThemes();

}
