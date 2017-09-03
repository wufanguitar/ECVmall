package com.frank.vmall.wechat;

import android.app.Activity;

import com.frank.vmall.app.ConfigKeys;
import com.frank.vmall.app.Mall;
import com.frank.vmall.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class MallWeChat {
    static final String WX_APP_ID = (String) Mall.getConfigurations().get(ConfigKeys.WE_CHAT_APP_ID);
    static final String WX_APP_SECRET = (String) Mall.getConfigurations().get(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mWeChatSignInCallback;

    private static final class Holder {
        private static final MallWeChat INSTANCE = new MallWeChat();
    }

    public static MallWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private MallWeChat() {
        final Activity activity = (Activity) Mall.getConfigurations().get(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, WX_APP_ID, true);
        WXAPI.registerApp(WX_APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public MallWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mWeChatSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mWeChatSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
