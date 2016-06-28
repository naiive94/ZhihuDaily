package com.naiive.zhihudaily.components.main;

import com.naiive.zhihudaily.common.base.AbsPresenter;
import com.naiive.zhihudaily.components.main.MainContract.Presenter;
import com.naiive.zhihudaily.data.source.TopicRepository;
import com.naiive.zhihudaily.model.Result;
import com.naiive.zhihudaily.model.view.DrawerItem;
import com.naiive.zhihudaily.utils.Relper;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by wangzhe on 16/6/18.
 */
public class MainPresenter extends AbsPresenter implements Presenter {

    private final MainContract.View mView;
    private final TopicRepository mRepository;

    private int drawPosition = 0;
    private boolean firstLoad = true;

    public MainPresenter(MainContract.View mView, TopicRepository mRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
    }

    @Override
    public void subscribe() {
        getDrawerList();
    }

    @Override
    public void unSubscribe() {
        clearSubscription();
    }

    @Override
    public void getDrawerList() {
        clearSubscription();
        loadDrawerList(firstLoad);
        firstLoad = false;
    }

    public void updateDrawerList() {
        loadDrawerList(true);
    }

    /**
     * use this method when update is needed
     *
     * @param forceUpdate
     */
    private void loadDrawerList(boolean forceUpdate) {
        if (forceUpdate)
            mRepository.refreshThemes();

        Subscription subscription = mRepository.convertDrawerItems()
                .compose(Relper.<List<DrawerItem>>applySchedulersIoMain())
                .subscribe(new Action1<List<DrawerItem>>() {
                    @Override
                    public void call(List<DrawerItem> drawerItems) {
                        mView.showDrawerItems(drawerItems);
                        mView.hide();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showEmpty();
                    }
                });

        addSubscription(subscription);
    }
}
