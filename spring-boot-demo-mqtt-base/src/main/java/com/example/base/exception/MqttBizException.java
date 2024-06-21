package com.example.base.exception;

/**
 * 定义通用业务异常
 * TODO 错误码
 *
 * @author yingxian.cyx
 * @date Created in 2024/6/21
 */
public class MqttBizException extends RuntimeException {

    public MqttBizException() {
        super();
    }

    public MqttBizException(String message) {
        super(message);
    }

    public MqttBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqttBizException(Throwable cause) {
        super(cause);
    }
}
