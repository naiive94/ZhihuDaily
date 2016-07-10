package com.naiive.zhihudaily.data.source;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;

import rx.Observable;

/**
 * Created by wangzhe on 16/7/9.
 */
public interface ArticleDataSource extends DataSource {

    Observable<ArticleContent> getArticle(int id);

    Observable<ArticleInfoBean> getArticleExtra(int id);

    Observable<String> getCss(String url);


}
