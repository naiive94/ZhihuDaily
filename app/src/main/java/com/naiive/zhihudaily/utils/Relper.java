package com.naiive.zhihudaily.utils;

import com.naiive.zhihudaily.model.Result;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangzhe on 16/6/28.
 */
public class Relper {

    private static final Map<String, Observable.Transformer> map = new HashMap<>();



    @Deprecated
    public static <T> Observable.Transformer<T,Result<T>> wrapResultUpdated(){
        return wrapResult(Result.Code.DATA_WITH_UPDATED);
    }

    @Deprecated
    public static <T> Observable.Transformer<T,Result<T>> wrapResultNotUpdated(){
        return wrapResult(Result.Code.DATA_NOT_UPDATED);
    }

    @Deprecated
    public static <T> Observable.Transformer<T,Result<T>> wrapResultError(){
        return wrapResult(Result.Code.DATA_NULL);
    }

    @Deprecated
    public static <T> Observable.Transformer<T,Result<T>> wrapResult(final int code){
        return new Observable.Transformer<T, Result<T>>() {
            @Override
            public Observable<Result<T>> call(Observable<T> tObservable) {
                return tObservable.map(new Func1<T, Result<T>>() {
                    @Override
                    public Result<T> call(T t) {
                        return createResult(code,t);
                    }
                });
            }
        };
    }

    private static <T> Result<T> createResult(int code,T result){
        return new Result<>(code,result);
    }

    public static <T> Observable.Transformer<T, T> applySchedulersIoMain() {
        if (map.get(TransKey.SCHEDULER_IO_MAIN)==null){
            map.put(TransKey.SCHEDULER_IO_MAIN, createSchedulersIoMain());
        }
        return (Observable.Transformer<T, T>) map.get(TransKey.SCHEDULER_IO_MAIN);
    }

    private static <T> Observable.Transformer<T, T> createSchedulersIoMain() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private interface TransKey{
        String SCHEDULER_IO_MAIN = "IO_MAIN";
    }



}
