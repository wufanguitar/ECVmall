package com.frank.vmall.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class MallMultiItemEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    MallMultiItemEntityBuilder() {
        // 先清除之前的数据
        FIELDS.clear();
    }

    public final MallMultiItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MallMultiFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MallMultiItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MallMultiItemEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MallMultiItemEntity build() {
        return new MallMultiItemEntity(FIELDS);
    }
}
