package com.frank.vmall.delegates.web.event;

import com.frank.vmall.utils.log.MallLogger;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class UndefinedEvent extends Event {
    @Override
    public String execute(String params) {
        MallLogger.e("UndefinedEvent", params);
        return null;
    }
}
