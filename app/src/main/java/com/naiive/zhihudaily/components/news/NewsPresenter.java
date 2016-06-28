package com.naiive.zhihudaily.components.news;

import com.naiive.zhihudaily.common.base.AbsPresenter;
import com.naiive.zhihudaily.data.source.NewsRepository;
import com.naiive.zhihudaily.model.News;
import com.naiive.zhihudaily.model.Story;
import com.naiive.zhihudaily.model.TopStory;
import com.naiive.zhihudaily.model.view.NewsItem;
import com.naiive.zhihudaily.utils.Relper;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wangzhe on 16/5/22.
 */
public class NewsPresenter extends AbsPresenter implements NewsContract.Presenter {

    private final NewsRepository mRepository;
    private final NewsContract.View mView;

    private boolean isFirst = true;

    public NewsPresenter(NewsContract.View mView, NewsRepository mRepository) {
        this.mRepository = mRepository;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        clearSubscription();
        loadNews(isFirst);
        isFirst = false;
    }

    @Override
    public void unSubscribe() {
        clearSubscription();
    }


    private void loadNews(boolean forceUpdate){
        if (forceUpdate)
            mRepository.refreshNews();

        mRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        mView.showNewsList(mRepository.convertStories(news));

                        List<String[]> list = mRepository.convertTopStories(news);
                        mView.showTopBanner(list.get(0), list.get(1));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void loadMoreNews(){
        mRepository.getMoreNews()
                .compose(Relper.<List<NewsItem>>applySchedulersIoMain())
                .subscribe(new Action1<List<NewsItem>>() {
                    @Override
                    public void call(List<NewsItem> newsItems) {
                        mView.addNewsList(newsItems);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }






}
