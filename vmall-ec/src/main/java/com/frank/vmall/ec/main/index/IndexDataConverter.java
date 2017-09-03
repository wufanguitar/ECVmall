package com.frank.vmall.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frank.vmall.app.Mall;
import com.frank.vmall.ui.recycler.DataConverter;
import com.frank.vmall.ui.recycler.ItemType;
import com.frank.vmall.ui.recycler.MallMultiFields;
import com.frank.vmall.ui.recycler.MallMultiItemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MallMultiItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int goodsId = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.IMAGE_TEXT;
            } else if (banners != null) {
                type = ItemType.BANNER;
                // Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MallMultiItemEntity entity = MallMultiItemEntity.builder()
                    .setField(MallMultiFields.ITEM_TYPE, type)
                    .setField(MallMultiFields.SPAN_SIZE, spanSize)
                    .setField(MallMultiFields.ID, goodsId)
                    .setField(MallMultiFields.TEXT, text)
                    .setField(MallMultiFields.IMAGE_URL, imageUrl)
                    .setField(MallMultiFields.BANNERS, bannerImages)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
