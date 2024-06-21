package com.example.consumer.controller;

import com.example.base.exception.MqttBizException;
import com.example.base.util.BaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yingxian.cyx
 * @date Created in 2024/6/21
 */

@RestControllerAdvice
public abstract class AbstractController {

    @ExceptionHandler(MqttBizException.class)
    public ResponseEntity<BaseResult<Object>> handleMqttBizException(MqttBizException ex) {
        BaseResult<Object> result = BaseResult.fail(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResult<Object>> handleGeneralException(Exception ex) {
        BaseResult<Object> result = BaseResult.fail("An unexpected error occurred");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
