package com.frank.vmall.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.frank.vmall.delegates.web.event.Event;
import com.frank.vmall.delegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

public class Configurator {
    // 由于Configurator会在Application生命周期内有用，而WeakHashMap中的键值对当不用时会被系统给回收掉
    // 因此，这里不建议用WeakHashMap
    private static final HashMap<Object, Object> MALL_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        MALL_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        MALL_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getMallConfigs() {
        return MALL_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 初始化配置
     */
    public final void configure() {
        initIcons();
        MALL_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        MALL_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        MALL_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        MALL_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppID(String wechatAppID) {
        MALL_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, wechatAppID);
        return this;
    }

    public final Configurator withWeChatAppSecret(String weChatAppSecret) {
        MALL_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, weChatAppSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        MALL_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public final Configurator withJavascriptInterface(@NonNull String name) {
        MALL_CONFIGS.put(ConfigKeys.JAVASCRIPT, name);
        return this;
    }

    public final Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager eventManager = EventManager.getInstance();
        eventManager.addEvent(name, event);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) MALL_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, please call the method of " +
                    "Configurator.configure()");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = MALL_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "is null");
        }
        return (T) MALL_CONFIGS.get(key);
    }
}
