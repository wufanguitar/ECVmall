package com.frank.vmall.ec.sign;

import android.provider.ContactsContract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frank.vmall.app.AccountManager;
import com.frank.vmall.ec.database.DatabaseManager;
import com.frank.vmall.ec.database.UserProfile;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class SignHandler {
    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insert(profile);

        // 注册成功并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insert(profile);

        // 登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
