package com.frank.ecvmall.generators;

import com.frank.vmall.annotations.AppRegisterGenerator;
import com.frank.vmall.wechat.templates.WXAppRegisterTemplate;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

@AppRegisterGenerator(
        packageName = "com.frank.ecvmall",
        appRegisterTemplate = WXAppRegisterTemplate.class
)
public interface WeChatAppRegister {
}
