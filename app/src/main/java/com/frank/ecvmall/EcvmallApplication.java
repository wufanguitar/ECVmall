package com.frank.ecvmall;

import android.app.Application;

import com.frank.vmall.app.Mall;
import com.frank.vmall.ec.database.DatabaseManager;
import com.frank.vmall.ec.icon.FontEcModule;
import com.frank.vmall.net.interceptor.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class EcvmallApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mall.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);

    }
}
