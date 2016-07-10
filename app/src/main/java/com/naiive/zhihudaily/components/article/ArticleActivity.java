package com.naiive.zhihudaily.components.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.naiive.zhihudaily.Injection;
import com.naiive.zhihudaily.R;
import com.naiive.zhihudaily.common.base.BaseActivity;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;
import com.naiive.zhihudaily.utils.ImageUtil;

/**
 * Created by wangzhe on 16/7/8.
 */
public class ArticleActivity extends BaseActivity implements ArticleContract.View {

    private TextView mHeaderTitle, mHeaderCorner;
    private ImageView mHeaderImage;
    private WebView mWebView;
    private Toolbar mToolbar;
    private TextView menuPraise, menuComment;
    private ImageView mCollect, mShare, mBack;

    private ArticlePresenter mPresenter;

    @Override
    protected void onBindView(Bundle savedInstanceState) {
        mHeaderTitle = view$(R.id.article_content_top_title);
        mHeaderCorner = view$(R.id.article_content_top_text);
        mHeaderImage = view$(R.id.article_content_top_img);
        mWebView = view$(R.id.article_webview);
        mToolbar = view$(R.id.article_toolbar);
        menuPraise = view$(R.id.article_menu_praise);
        menuComment = view$(R.id.article_menu_comment);
        mCollect = view$(R.id.article_menu_collect);
        mShare = view$(R.id.article_menu_share);
        mBack = view$(R.id.article_back);

    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        menuPraise.setOnClickListener(new View.OnClickListener() {
            boolean isPraised = false;

            @Override
            public void onClick(View v) {
                isPraised = !isPraised;
                setDrawable(menuPraise, isPraised ? R.drawable.ic_praised : R.drawable.ic_praise, 0);
                if (isPraised) mPresenter.praise();
                else mPresenter.unPraise();
            }
        });

    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        mPresenter = new ArticlePresenter(this, Injection.provideArticleRepository());
        int id = getIntent().getExtras().getInt("id");
        mPresenter.setId(id);
        mPresenter.getArticleContent();
        mPresenter.getArticleExtraInfo();
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
        return R.layout.activity_article;
    }


    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
    }

    @Override
    public void loading() {

    }

    @Override
    public void error() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void setExtraData(ArticleInfoBean content) {
        menuPraise.setText(String.valueOf(content.getPopularity()));
        menuComment.setText(String.valueOf(content.getComments()));
    }

    @Override
    public void onContentSuccess(ArticleContent content, String html) {
        ImageUtil.load(content.getImage(), mHeaderImage);
        mHeaderTitle.setText(content.getTitle());
        mHeaderCorner.setText(content.getImageSource());
        mWebView.loadDataWithBaseURL("", html, "text/html", "UTF-8", null);
    }

    @Override
    public void addPraise() {
        String num = menuPraise.getText().toString();
        if (!TextUtils.isDigitsOnly(num)) return;
        Integer i = Integer.valueOf(num);
        menuPraise.setText(String.valueOf(i + 1));

    }

    @Override
    public void minusPraise() {
        String num = menuPraise.getText().toString();
        if (!TextUtils.isDigitsOnly(num)) return;
        Integer i = Integer.valueOf(num);
        menuPraise.setText(String.valueOf(i - 1));
    }

    public static Intent getCallingIntent(Context context, int id) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("id", id);
        return intent;
    }


}
