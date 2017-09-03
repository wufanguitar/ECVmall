package com.frank.vmall.ui.recycler;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public abstract class DataConverter {
    protected final ArrayList<MallMultiItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData;

    public abstract ArrayList<MallMultiItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("get data is null");
        }
        return mJsonData;
    }
}
