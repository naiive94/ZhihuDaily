package com.naiive.zhihudaily.common.base;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by wangzhe on 16/5/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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


    protected abstract
    @LayoutRes
    int setContentViewId();

    protected void onViewCreated() {

    }


    protected
    @IdRes
    int getFragmentContainerId() {
        return 0;
    }

    public <T extends BaseFragment> T findFragment(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    public <T extends BaseFragment> void changeFragment(T fragment, int res) {
        changeFragment(fragment, res, fragment.getClass().getName());
    }

    public <T extends BaseFragment> void changeFragment(T fragment, int res, String tag) {
        changeFragment(fragment, res, tag, null);
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

    /**
     * 添加Fragment
     *
     * @param fragment
     */
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContainerId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 回退fragment
     */
    protected void popBackStackFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
    }

    protected void setDrawable(TextView mView, @DrawableRes int res, int direction) {
        Drawable mDrawable = ResourcesCompat.getDrawable(getResources(), res, null);
        setDrawable(mView, mDrawable, direction);
    }

    protected void setDrawable(TextView mView, Drawable mDrawable, int direction) {
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        switch (direction) {
            case 0:
                mView.setCompoundDrawables(mDrawable, null, null, null);
                break;
            case 1:
                mView.setCompoundDrawables(null, mDrawable, null, null);
                break;
            case 2:
                mView.setCompoundDrawables(null, null, mDrawable, null);
                break;
            case 3:
                mView.setCompoundDrawables(null, null, null, mDrawable);
                break;
        }

    }


}
