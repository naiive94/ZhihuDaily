package com.naiive.zhihudaily.components.main;

import com.naiive.zhihudaily.common.base.BasePresenter;
import com.naiive.zhihudaily.common.base.BaseView;
import com.naiive.zhihudaily.model.view.DrawerItem;

import java.util.List;

/**
 * Created by wangzhe on 16/6/18.
 */
public interface MainContract {

    interface View extends BaseView<Presenter>{

        void showDrawerItems(List<DrawerItem> list);

        void showEmpty();

        void hide();

    }

    interface Presenter extends BasePresenter{

        void getDrawerList();
    }
}
