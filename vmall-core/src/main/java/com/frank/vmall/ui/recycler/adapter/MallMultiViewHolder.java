package com.frank.vmall.ui.recycler.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import retrofit2.http.Multipart;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class MallMultiViewHolder extends BaseViewHolder {
    private MallMultiViewHolder(View view) {
        super(view);
    }

    public static MallMultiViewHolder create(View view) {
        return new MallMultiViewHolder(view);
    }
}
