package com.frank.vmall.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/27 0027.
 * 静态工具类
 */

public final class Mall {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getMallConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
