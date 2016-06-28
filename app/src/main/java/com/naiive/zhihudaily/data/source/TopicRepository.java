package com.naiive.zhihudaily.data.source;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.data.source.local.TopicLocalDataSource;
import com.naiive.zhihudaily.data.source.remote.TopicRemoteDataSource;
import com.naiive.zhihudaily.model.Result;
import com.naiive.zhihudaily.model.Topic;
import com.naiive.zhihudaily.model.view.DrawerItem;
import com.naiive.zhihudaily.utils.LocalCacheUtil;
import com.naiive.zhihudaily.utils.LogUtil;
import com.naiive.zhihudaily.utils.Relper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/6/18.
 */
public class TopicRepository implements DataSource {

    private final TopicDataSource mLocalDataSource;
    private final TopicDataSource mRemoteDataSource;

    private boolean forceUpdate = true;
    private Topic mCacheTopic;

    public TopicRepository() {
        mLocalDataSource = new TopicLocalDataSource();
        mRemoteDataSource = new TopicRemoteDataSource();
    }

    private Observable<Topic> getThemesList() {
        //提供最准确的数据
        if (mCacheTopic != null && !forceUpdate) {
            return Observable.just(mCacheTopic);
        }

        Observable<Topic> remote = mRemoteDataSource.getThemesList();

        Observable<Topic> local = mLocalDataSource.getThemesList();

        if (forceUpdate) {
            return remote;
        }

        return Observable.concat(local, remote).first();
    }

    public Observable<List<DrawerItem>> convertDrawerItems() {
        return getThemesList()
                .map(new Func1<Topic, List<DrawerItem>>() {
                    @Override
                    public List<DrawerItem> call(Topic topic) {
                        List<DrawerItem> list = new ArrayList<>();
                        for (Topic.Other item : topic.getSubscribed()) {
                            list.add(new DrawerItem(item.getName(), 1));
                        }
                        for (Topic.Other item : topic.getOthers()) {
                            list.add(new DrawerItem(item.getName(), 0));
                        }
                        return list;
                    }
                });
    }

    public void refreshThemes() {
        forceUpdate = true;
    }

}
