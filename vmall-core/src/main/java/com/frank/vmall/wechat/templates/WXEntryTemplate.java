package com.frank.vmall.wechat.templates;

import com.frank.vmall.activities.ProxyActivity;
import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.wechat.BaseWXEntryActivity;
import com.frank.vmall.wechat.MallWeChat;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        MallWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
