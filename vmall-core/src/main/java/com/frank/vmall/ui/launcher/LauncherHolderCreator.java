package com.frank.vmall.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class LauncherHolderCreator implements CBViewHolderCreator{
    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
