package com.naiive.zhihudaily.common.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wangzhe on 16/6/17.
 */
public abstract class AbsPresenter {

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    protected void addSubscription(Subscription subscription){
        mSubscriptions.add(subscription);
    }

    protected void clearSubscription(){
        mSubscriptions.clear();
    }

}
