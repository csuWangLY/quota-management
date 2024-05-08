package com.project.quotamanagement.quotamanagement.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getCurrentDateAsYYYYMMDD(LocalDateTime date) {

        // 定义日期格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 格式化日期为yyyyMMdd格式
        return date.format(formatter);
    }
}
