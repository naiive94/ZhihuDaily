package com.naiive.zhihudaily.common.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by wangzhe on 16/5/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        onBindView(savedInstanceState);
        onInitView(savedInstanceState);
        onViewCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onSubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onUnSubscribe();
    }

    protected abstract void onBindView(Bundle savedInstanceState);

    protected abstract void onInitView(Bundle savedInstanceState);

    protected abstract void onSubscribe();

    protected abstract void onUnSubscribe();

    protected abstract @LayoutRes int setContentViewId();

    protected void onViewCreated(){

    }


    protected @IdRes int getFragmentContainerId(){
        return 0;
    }

    public <T extends BaseFragment> T findFragment(String tag){
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    public <T extends BaseFragment> void changeFragment(T fragment, int res){
        changeFragment(fragment,res,fragment.getClass().getName());
    }

    public <T extends BaseFragment> void changeFragment(T fragment, int res, String tag){
        changeFragment(fragment,res,tag,null);
    }

    public <T extends BaseFragment> void changeFragment(T fragment, int res, String tag, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (res == 0) {
            throw new Resources.NotFoundException("FragmentContainer is null");
        }
        if (tag == null) {
            tag = fragment.getClass().getName();
        }
        if (currentFragment != null) {
            if (tag.equals(currentFragment.getTag())) {
                return;
            }
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.add(res, fragment, tag);
        }
        if (fragment.isHidden()) {
            transaction.show(fragment);
        }
        if (currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }
        currentFragment = fragment;
        transaction.commit();
    }


    public <V extends View> V view$(@IdRes int id) {

        return (V) findViewById(id);
    }

    public <V extends View> V view$(View view, @IdRes int id) {

        return (V) view.findViewById(id);
    }
}
