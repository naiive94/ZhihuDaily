package com.naiive.zhihudaily.common.api;

import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;
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
    Observable<News> getMoreNews(@Path("date") String beforeDate);

    @GET("themes")
    Observable<Topic> getThemes();

    @GET("news/{id}")
    Observable<ArticleContent> getArticle(@Path("id") int id);

    @GET("story-extra/{id}")
    Observable<ArticleInfoBean> getArticleExtraInfo(@Path("id") int id);
}
