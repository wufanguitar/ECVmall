package com.frank.vmall.ui.recycler.decoration;

import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static final BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
