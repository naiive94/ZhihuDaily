package com.naiive.zhihudaily.components.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.naiive.zhihudaily.Injection;
import com.naiive.zhihudaily.R;
import com.naiive.zhihudaily.common.base.BaseActivity;
import com.naiive.zhihudaily.components.news.NewsFragment;
import com.naiive.zhihudaily.components.news.NewsPresenter;
import com.naiive.zhihudaily.model.view.DrawerItem;

import java.util.List;

/**
 * Created by wangzhe on 16/5/22.
 */
public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, MainContract.View {

    private Toolbar mToolbar;
    private RecyclerView mDrawerList;
    private DrawerLayout mDrawerLayout;

    private MainPresenter mPresenter;
    private NewsPresenter mNewsPresenter;
    private DrawerItemAdapter mDrawerAdapter;
    private NewsFragment newsFragment;

    @Override
    protected void onBindView(Bundle savedInstanceState) {
        mToolbar = view$(R.id.main_toolbar);
        mDrawerList = view$(R.id.main_drawer);
        mDrawerLayout = view$(R.id.main_drawer_layout);
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            newsFragment = findFragment(NewsFragment.class.getName());
        } else {
            newsFragment = NewsFragment.newInstance();
        }

        setupToolbar();
        setupDrawers();

        changeFragment(newsFragment, getFragmentContainerId());
    }

    @Override
    protected void onViewCreated() {
        mPresenter = new MainPresenter(this, Injection.provideTopicRepository());
        mNewsPresenter = new NewsPresenter(newsFragment, Injection.provideNewsRepository());
    }

    @Override
    protected void onSubscribe() {
        mPresenter.subscribe();
    }

    @Override
    protected void onUnSubscribe() {
        mPresenter.unSubscribe();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    private void setupDrawers() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_string, R.string.close_string);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerAdapter = new DrawerItemAdapter(this, R.layout.main_item_drawer, null);
        mDrawerList.setAdapter(mDrawerAdapter);
        setHeaderView(mDrawerList);
        mDrawerAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DrawerItem item = (DrawerItem) baseQuickAdapter.getItem(i);
                switch (view.getId()) {

                }
            }
        });

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getResources().getString(R.string.homePage));
        mToolbar.setOnMenuItemClickListener(this);
    }

    private void setHeaderView(RecyclerView recyclerView) {
        View header = LayoutInflater.from(this).inflate(R.layout.main_header_drawer, recyclerView, false);
        mDrawerAdapter.addHeaderView(header);

    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.main_container;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void showDrawerItems(List<DrawerItem> list) {
        mDrawerAdapter.setNewData(list);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hide() {

    }
}
