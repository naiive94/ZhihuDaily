package com.naiive.zhihudaily;

import com.naiive.zhihudaily.data.DataSource;
import com.naiive.zhihudaily.data.source.ArticleRepository;
import com.naiive.zhihudaily.data.source.NewsRepository;
import com.naiive.zhihudaily.data.source.TopicRepository;

/**
 * Created by wangzhe on 16/5/23.
 */
public class Injection {

    public static NewsRepository provideNewsRepository(){
        return DataSource.Proxy.create().get(NewsRepository.class);
    }

    public static TopicRepository provideTopicRepository(){
        return DataSource.Proxy.create().get(TopicRepository.class);
    }

    public static ArticleRepository provideArticleRepository(){
        return DataSource.Proxy.create().get(ArticleRepository.class);
    }

}
