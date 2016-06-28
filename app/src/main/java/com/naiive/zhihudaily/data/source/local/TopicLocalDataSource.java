package com.naiive.zhihudaily.data.source.local;

import com.naiive.zhihudaily.data.source.TopicDataSource;
import com.naiive.zhihudaily.model.Topic;
import com.naiive.zhihudaily.model.view.DrawerItem;
import com.naiive.zhihudaily.utils.LocalCacheUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangzhe on 16/6/18.
 */
public class TopicLocalDataSource implements TopicDataSource {

    @Override
    public Observable<Topic> getThemesList() {
        return Observable.create(new Observable.OnSubscribe<Topic>() {
            @Override
            public void call(Subscriber<? super Topic> subscriber) {
                Topic topic = LocalCacheUtil.getDefault().fetchData(KEY_THEMES, Topic.class);
                if (topic != null)
                    subscriber.onNext(topic);
                subscriber.onCompleted();
            }
        });
    }

}
