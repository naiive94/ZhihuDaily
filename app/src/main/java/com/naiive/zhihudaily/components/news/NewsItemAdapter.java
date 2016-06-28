package com.naiive.zhihudaily.components.news;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.naiive.zhihudaily.R;
import com.naiive.zhihudaily.model.view.NewsItem;
import com.naiive.zhihudaily.utils.ImageUtil;

import java.util.List;

/**
 * Created by wangzhe on 16/6/24.
 */
public class NewsItemAdapter extends BaseQuickAdapter<NewsItem> {

    public NewsItemAdapter(Context context, int layoutResId, List<NewsItem> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsItem newsItem) {

        baseViewHolder.setText(R.id.title, newsItem.title);
        ImageUtil.load(newsItem.firstImage, (ImageView) baseViewHolder.getView(R.id.image));

    }
}
