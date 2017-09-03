package com.frank.vmall.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.detail.GoodsDetailDelegate;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class IndexItemClickListener extends SimpleClickListener {
    private final VmallDelegate DELEGATE;

    private IndexItemClickListener(VmallDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(VmallDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
