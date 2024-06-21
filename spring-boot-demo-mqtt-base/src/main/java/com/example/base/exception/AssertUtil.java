package com.example.base.exception;

import org.apache.commons.lang.StringUtils;

/**
 * 断言工具类
 *
 * @author yingxian.cyx
 * @date Created in 2024/6/21
 */
public class AssertUtil {

    // isTrue 方法
    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new MqttBizException("PARAMETER_ILLEGAL");
        }
    }

    public static void isNotBlank(String message) {
        if (StringUtils.isBlank(message)) {
            throw new MqttBizException("PARAMETER_ILLEGAL");
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new MqttBizException("PARAMETER_ILLEGAL");
        }
    }
}