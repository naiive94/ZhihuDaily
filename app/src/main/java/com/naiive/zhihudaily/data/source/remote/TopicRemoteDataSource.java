package com.naiive.zhihudaily.data.source.remote;

import com.naiive.zhihudaily.common.api.ZhihuFactory;
import com.naiive.zhihudaily.data.source.TopicDataSource;
import com.naiive.zhihudaily.model.Topic;
import com.naiive.zhihudaily.utils.LocalCacheUtil;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wangzhe on 16/6/18.
 */
public class TopicRemoteDataSource implements TopicDataSource {

    @Override
    public Observable<Topic> getThemesList() {
        return ZhihuFactory.getApi().getThemes().doOnNext(new Action1<Topic>() {
            @Override
            public void call(Topic topic) {
                LocalCacheUtil.getDefault().putData(KEY_THEMES, topic);
            }
        });
    }
}
