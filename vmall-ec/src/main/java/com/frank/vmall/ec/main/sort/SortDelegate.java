package com.frank.vmall.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.frank.vmall.delegates.bottom.BottomItemDelegate;
import com.frank.vmall.ec.R;
import com.frank.vmall.ec.main.sort.content.ContentDelegate;
import com.frank.vmall.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate verticalListDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, verticalListDelegate);
        // 设置右侧第一个分类显示，默认显示分类一
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
