package com.frank.ecvmall.generators;

import com.frank.vmall.annotations.EntryGenerator;
import com.frank.vmall.wechat.templates.WXEntryTemplate;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

@EntryGenerator(
        packageName = "com.frank.ecvmall",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
