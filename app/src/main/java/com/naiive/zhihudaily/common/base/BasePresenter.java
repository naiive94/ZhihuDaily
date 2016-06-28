package com.naiive.zhihudaily.common.base;

/**
 * Created by wangzhe on 16/5/22.
 */
public interface BasePresenter {

    void subscribe();

    void unSubscribe();

    interface Callback<T> {

        void onSuccess(T t);

        void onFailure(int code, String msg);

        void onCompleted();
    }

}