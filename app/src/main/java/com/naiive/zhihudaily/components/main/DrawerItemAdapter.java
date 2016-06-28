package com.naiive.zhihudaily.components.main;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.naiive.zhihudaily.R;
import com.naiive.zhihudaily.model.view.DrawerItem;

import java.util.List;

/**
 * Created by wangzhe on 16/6/18.
 */
public class DrawerItemAdapter extends BaseQuickAdapter<DrawerItem> {


    public DrawerItemAdapter(Context context, int layoutResId, List<DrawerItem> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DrawerItem drawerItem) {

        baseViewHolder.setText(R.id.title, drawerItem.name)
                .setImageResource(R.id.subscribe, drawerItem.isSubscribe == 1 ? R.drawable.ic_forward : R.drawable.ic_plus)
                .setOnClickListener(R.id.title, new OnItemChildClickListener())
                .setOnClickListener(R.id.subscribe, new OnItemChildClickListener());
    }
}
