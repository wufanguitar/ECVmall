package com.frank.vmall.delegates.bottom;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;


    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}

