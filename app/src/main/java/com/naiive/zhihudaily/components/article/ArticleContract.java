package com.naiive.zhihudaily.components.article;

import com.naiive.zhihudaily.common.base.BasePresenter;
import com.naiive.zhihudaily.common.base.BaseView;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;

/**
 * Created by wangzhe on 16/7/8.
 */
public interface ArticleContract {

    interface Presenter extends BasePresenter{

        void getArticleContent();

        void getArticleExtraInfo();

        void setId(int id);

        void praise();

        void unPraise();
    }

    interface View extends BaseView<Presenter>{

        void loading();

        void error();

        void hide();

        void setExtraData(ArticleInfoBean content);

        void onContentSuccess(ArticleContent content,String html);

        void addPraise();

        void minusPraise();




    }
}
