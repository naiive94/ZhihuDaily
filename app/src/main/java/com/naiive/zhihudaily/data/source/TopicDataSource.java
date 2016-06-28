package com.naiive.zhihudaily.data.source;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.model.Topic;
import com.naiive.zhihudaily.model.view.DrawerItem;

import java.util.List;

import rx.Observable;

/**
 * Created by wangzhe on 16/6/18.
 */
public interface TopicDataSource extends DataSource {

    String KEY_THEMES = "KEY_THEMES";

    Observable<Topic> getThemesList();

}
