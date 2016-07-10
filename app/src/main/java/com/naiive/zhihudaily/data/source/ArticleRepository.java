package com.naiive.zhihudaily.data.source;

import android.util.SparseArray;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.data.source.remote.ArticleRemoteDataSource;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/7/9.
 */
public class ArticleRepository implements DataSource {

    private ArticleDataSource mRemote;
    private ArticleDataSource mLocal;

    private SparseArray<ArticleContent> mCacheArticles;

    public ArticleRepository() {
        mRemote = new ArticleRemoteDataSource();
        mCacheArticles = new SparseArray<>(5);
    }

    public Observable<ArticleContent> getArticle(final int id) {
        Observable<ArticleContent> articleContentObservable;
        if (mCacheArticles.get(id) != null)
            articleContentObservable = Observable.just(mCacheArticles.get(id));
        else {
            articleContentObservable = mRemote.getArticle(id).doOnNext(new Action1<ArticleContent>() {
                @Override
                public void call(ArticleContent content) {
                    mCacheArticles.put(id, content);
                }
            });
        }

        return articleContentObservable;
    }

    public Observable<String> getCss(String url) {
        return mRemote.getCss(url);
    }

    public Observable<ArticleInfoBean> getArticleExtraInfo(int id) {
        return mRemote.getArticleExtra(id);
    }

    public ArticleContent getCache(final int id) {
        return mCacheArticles.get(id);
    }


}
