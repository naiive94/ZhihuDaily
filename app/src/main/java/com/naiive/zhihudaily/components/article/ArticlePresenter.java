package com.naiive.zhihudaily.components.article;

import com.naiive.zhihudaily.AppContext;
import com.naiive.zhihudaily.common.base.AbsPresenter;
import com.naiive.zhihudaily.data.source.ArticleRepository;
import com.naiive.zhihudaily.model.ArticleContent;
import com.naiive.zhihudaily.model.ArticleInfoBean;
import com.naiive.zhihudaily.utils.Relper;

import java.io.File;
import java.io.FileOutputStream;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by wangzhe on 16/7/8.
 */
public class ArticlePresenter extends AbsPresenter implements ArticleContract.Presenter {

    private final ArticleContract.View mView;
    private final ArticleRepository mRepository;
    private String fileName = "article_content.css";

    private int id = -1;

    public ArticlePresenter(ArticleContract.View mView, ArticleRepository mRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        clearSubscription();
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void praise() {
        mView.addPraise();
    }

    @Override
    public void unPraise() {
        mView.minusPraise();
    }

    @Override
    public void getArticleContent() {
        mView.hide();
        mView.loading();
        addSubscription(mRepository.getArticle(id)
                        .flatMap(new Func1<ArticleContent, Observable<String>>() {
                            @Override
                            public Observable<String> call(ArticleContent content) {
                                return mRepository.getCss(content.getCss().get(0));
                            }
                        })
                        .doOnNext(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                File file = new File(AppContext.getContext().getFilesDir(), fileName);
                                if (file.exists() && file.length() > 10) return;
                                try {
                                    FileOutputStream outputStream = new FileOutputStream(file);
                                    outputStream.write(s.getBytes());
                                    outputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .compose(Relper.<String>applySchedulersIoMain())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {

                                ArticleContent articleContent = mRepository.getCache(id);
                                String css = "<link rel=\"stylesheet\" href=\"file:///" + AppContext.getContext().getFilesDir() + "/" + fileName + "\"type=\"text/css\" />";
                                String data = "<html><head>" + css + "</head><body>" + articleContent.getBody() + "</body></html>";
                                data = data.replace("<div class=\"img-place-holder\">", "");

                                mView.onContentSuccess(articleContent, data);
                                mView.hide();

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                mView.hide();
                                mView.error();
                            }
                        })
        );
    }

    @Override
    public void getArticleExtraInfo() {
        addSubscription(mRepository.getArticleExtraInfo(id)
                .compose(Relper.<ArticleInfoBean>applySchedulersIoMain())
                .subscribe(new Action1<ArticleInfoBean>() {
                    @Override
                    public void call(ArticleInfoBean articleInfoBean) {
                        mView.setExtraData(articleInfoBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));


    }

}