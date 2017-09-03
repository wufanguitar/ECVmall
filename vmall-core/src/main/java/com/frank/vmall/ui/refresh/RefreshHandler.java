package com.frank.vmall.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.frank.vmall.app.Mall;
import com.frank.vmall.net.RestfulClient;
import com.frank.vmall.net.callback.ISuccess;
import com.frank.vmall.ui.recycler.DataConverter;
import com.frank.vmall.ui.recycler.adapter.MallMultiRecyclerAdapter;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener
{
    private final SwipeRefreshLayout SWIPE_REFRESH_LAYOUT;
    private final PagingBean PAGING_BEAN;
    private final RecyclerView RECYCLERVIEW;
    private final DataConverter DATA_CONVERTER;
    private MallMultiRecyclerAdapter mAdapter;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter dataConverter,
                           PagingBean pagingBean) {
        this.SWIPE_REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.DATA_CONVERTER = dataConverter;
        this.PAGING_BEAN = pagingBean;
        SWIPE_REFRESH_LAYOUT.setOnRefreshListener(this);

    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        SWIPE_REFRESH_LAYOUT.setRefreshing(true);
        Mall.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行一些网络请求
                SWIPE_REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {
        PAGING_BEAN.setDelayed(1000);
        RestfulClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        PAGING_BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        // 设置Adapter
                        mAdapter = MallMultiRecyclerAdapter.create(DATA_CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        PAGING_BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
