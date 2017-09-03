package com.frank.vmall.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.R;
import com.frank.vmall.ec.R2;
import com.frank.vmall.ec.main.sort.SortDelegate;
import com.frank.vmall.net.RestfulClient;
import com.frank.vmall.net.callback.ISuccess;
import com.frank.vmall.ui.recycler.MallMultiItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class VerticalListDelegate extends VmallDelegate{

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mSortListRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mSortListRecyclerView.setLayoutManager(manager);
        // 屏蔽动画效果
        mSortListRecyclerView.setItemAnimator(null);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestfulClient.builder()
                .url("http://192.168.3.16/RestServer/api/sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MallMultiItemEntity> data =
                                new SortListDataConverter()
                                        .setJsonData(response)
                                        .convert();
                        final SortDelegate sortDelegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, sortDelegate);
                        mSortListRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
