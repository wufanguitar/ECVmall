package com.frank.vmall.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.frank.vmall.delegates.web.event.Event;
import com.frank.vmall.delegates.web.event.EventManager;

/**
 * Created by Administrator on 2017/9/2 0002.
 * 用来与原生进行交互
 */

final class MallWebInterface {
    private final WebDelegate WEB_DELEGATE;

    private MallWebInterface(WebDelegate webDelegate) {
        this.WEB_DELEGATE = webDelegate;
    }

    static MallWebInterface create(WebDelegate webDelegate) {
        return new MallWebInterface(webDelegate);
    }

    /*
       Android 4.4以后必须添加该注解，否则会被认为是不安全不可用的
     */
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(WEB_DELEGATE);
            event.setContext(WEB_DELEGATE.getContext());
            event.setUrl(WEB_DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
