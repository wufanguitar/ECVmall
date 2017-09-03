package com.frank.ecvmall.generators;

import com.frank.vmall.annotations.PayEntryGenerator;
import com.frank.vmall.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

@PayEntryGenerator(
        packageName = "com.frank.ecvmall",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
