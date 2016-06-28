package com.naiive.zhihudaily.components.news;

import com.naiive.zhihudaily.common.base.BasePresenter;
import com.naiive.zhihudaily.common.base.BaseView;
import com.naiive.zhihudaily.model.view.NewsItem;

import java.util.List;

/**
 * Created by wangzhe on 16/5/22.
 */
public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void showTopBanner(String[] images,String[] titles);

        void showNewsList(List<NewsItem> stories);

        void addNewsList(List<NewsItem> stories);
    }

    interface Presenter extends BasePresenter {

        void subscribe();

        void loadMoreNews();

    }
}
