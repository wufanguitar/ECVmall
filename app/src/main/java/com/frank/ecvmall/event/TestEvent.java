package com.frank.ecvmall.event;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

import com.frank.vmall.delegates.web.event.Event;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
