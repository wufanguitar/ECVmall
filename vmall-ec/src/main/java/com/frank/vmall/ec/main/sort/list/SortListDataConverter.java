package com.frank.vmall.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frank.vmall.ui.recycler.DataConverter;
import com.frank.vmall.ui.recycler.ItemType;
import com.frank.vmall.ui.recycler.MallMultiFields;
import com.frank.vmall.ui.recycler.MallMultiItemEntity;
import com.frank.vmall.ui.recycler.MallMultiItemEntityBuilder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class SortListDataConverter extends DataConverter {
    @Override
    public ArrayList<MallMultiItemEntity> convert() {
        final ArrayList<MallMultiItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int sortId = data.getInteger("id");
            final String name = data.getString("name");

            final MallMultiItemEntity entity = MallMultiItemEntity.builder()
                    .setField(MallMultiFields.ITEM_TYPE, ItemType.SORT_MENU_LIST)
                    .setField(MallMultiFields.ID, sortId)
                    .setField(MallMultiFields.TEXT, name)
                    .setField(MallMultiFields.TAG, false)
                    .build();
            dataList.add(entity);
            // 设置第一个被选中
            dataList.get(0).setField(MallMultiFields.TAG, true);
        }
        return dataList;
    }
}
