package com.naiive.zhihudaily.data.source.remote;

import com.naiive.zhihudaily.common.api.CommonApi;
import com.naiive.zhihudaily.common.api.ZhihuApi;
import com.naiive.zhihudaily.common.api.ZhihuFactory;
import com.naiive.zhihudaily.data.source.ArticleDataSource;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;
import com.naiive.zhihudaily.utils.OkHttpUtil;

import java.io.IOException;

import rx.Observable;

/**
 * Created by wangzhe on 16/7/9.
 */
public class ArticleRemoteDataSource implements ArticleDataSource {

    private ZhihuApi api = ZhihuFactory.getApi();
    private CommonApi commonApi;

    @Override
    public Observable<ArticleContent> getArticle(int id) {
        return api.getArticle(id);
    }

    @Override
    public Observable<String> getCss(String url) {
        try {
            return Observable.just(OkHttpUtil.getInstance().get(url));
        } catch (IOException e) {
            e.printStackTrace();
            return Observable.empty();
        }
    }

    @Override
    public Observable<ArticleInfoBean> getArticleExtra(int id) {
        return api.getArticleExtraInfo(id);
    }
}
