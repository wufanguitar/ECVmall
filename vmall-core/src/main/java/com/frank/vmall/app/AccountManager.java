package com.frank.vmall.app;

import com.frank.vmall.utils.storage.MallPreference;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    // 保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        MallPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return MallPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
