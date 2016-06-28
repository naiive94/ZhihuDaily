package com.naiive.zhihudaily.components.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.naiive.zhihudaily.R;
import com.naiive.zhihudaily.common.base.BaseFragment;
import com.naiive.zhihudaily.model.view.NewsItem;
import com.naiive.zhihudaily.support.EndlessScrollListener;
import com.naiive.zhihudaily.utils.ImageUtil;
import com.naiive.zhihudaily.utils.LogUtil;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by wangzhe on 16/5/22.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View {

    private NewsPresenter mPresenter;
    private NewsItemAdapter mAdapter;

    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private Banner.OnLoadImageListener onLoadImageListener = new Banner.OnLoadImageListener() {
        @Override
        public void OnLoadImage(ImageView view, Object url) {
            ImageUtil.load((String) url, view);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState) {
        mRecyclerView = view$(R.id.recyclerView);
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new NewsItemAdapter(mContext, R.layout.news_item_news, null);
        mRecyclerView.setAdapter(mAdapter);
        setHeader(mRecyclerView);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
        mRecyclerView.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreNews();
            }
        });
    }

    @Override
    protected void onSubscribe() {
        mPresenter.subscribe();
    }

    @Override
    protected void onUnSubscribe() {
        mPresenter.unSubscribe();
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = (NewsPresenter) presenter;
    }

    private void setHeader(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() == null)
            throw new IllegalArgumentException();
        View header = LayoutInflater.from(getApplicationContext()).inflate(R.layout.news_header_banner, recyclerView, false);
        ((NewsItemAdapter) recyclerView.getAdapter()).addHeaderView(header);
        mBanner = view$(header, R.id.imageBanner);
        mBanner.setBannerStyle(Banner.NUM_INDICATOR_TITLE);
        mBanner.isAutoPlay(false);  //其自带计时器有问题，会开启多个计时
        mBanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {

            }
        });


    }

    @Override
    public void showTopBanner(String[] images, String[] titles) {
        mBanner.setImages(images, onLoadImageListener);
        mBanner.setBannerTitle(titles);
    }

    @Override
    public void showNewsList(List<NewsItem> stories) {
        mAdapter.setNewData(stories);
    }

    @Override
    public void addNewsList(List<NewsItem> stories) {
        List<NewsItem> newsItems = mAdapter.getData();
        newsItems.addAll(stories);
        mAdapter.setNewData(newsItems);
    }

}
