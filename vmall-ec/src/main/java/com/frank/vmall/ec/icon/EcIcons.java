package com.frank.vmall.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue66e'),
    icon_ali_pay('\ue67c');

    private char character;;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return this.name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
