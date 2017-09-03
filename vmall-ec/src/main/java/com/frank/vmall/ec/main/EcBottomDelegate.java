package com.frank.vmall.ec.main;

import android.graphics.Color;

import com.frank.vmall.delegates.bottom.BaseBottomDelegate;
import com.frank.vmall.delegates.bottom.BottomItemDelegate;
import com.frank.vmall.delegates.bottom.BottomTabBean;
import com.frank.vmall.delegates.bottom.ItemBuilder;
import com.frank.vmall.ec.main.index.IndexDelegate;
import com.frank.vmall.ec.main.sort.SortDelegate;
import com.frank.vmall.ec.main.discovery.DiscoveryDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoveryDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
