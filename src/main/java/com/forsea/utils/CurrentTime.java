package com.forsea.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class CurrentTime {
    /**
     * 获取当前时间
     * @return 当前时间的字符串
     */
    public static String getCurrentTime() {
        // 获取时间并格式化为：2021-08-11 15:15:23
        DateTime date = DateUtil.date();
        String currentTime = DateUtil.formatDateTime(date);

        return currentTime;
    }
}
