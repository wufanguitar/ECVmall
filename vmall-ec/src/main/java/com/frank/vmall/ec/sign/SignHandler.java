package com.frank.vmall.ec.sign;

import android.provider.ContactsContract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frank.vmall.ec.database.DatabaseManager;
import com.frank.vmall.ec.database.UserProfile;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class SignHandler {
    public static void onSignUp(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insert(profile);

    }
}
