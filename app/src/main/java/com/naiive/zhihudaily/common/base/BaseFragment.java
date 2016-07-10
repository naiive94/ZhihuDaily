package com.naiive.zhihudaily.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naiive.zhihudaily.AppContext;


/**
 * Created by wangzhe on 16/5/22.
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null)
            mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBindView(savedInstanceState);
        onInitView(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        onSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        onUnSubscribe();
    }

    protected Context getApplicationContext() {
        return getAttachContext() == null ? AppContext.getContext() : getAttachContext().getApplicationContext();
    }

    protected Context getAttachContext(){
        return mContext;
    }

    protected abstract int getLayoutId();

    protected abstract void onBindView(Bundle savedInstanceState);

    protected abstract void onInitView(Bundle savedInstanceState);

    protected abstract void onSubscribe();

    protected abstract void onUnSubscribe();

    public <V extends View> V view$(@IdRes int id) {

        return view$(getView(), id);
    }

    public <V extends View> V view$(View view, @IdRes int id) {

        return (V) view.findViewById(id);
    }

}
