package com.cs.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author LivePulse
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        log.error("系统异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "系统异常: " + e.getMessage());
        result.put("data", null);
        return result;
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "运行时异常: " + e.getMessage());
        result.put("data", null);
        return result;
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "非法参数: " + e.getMessage());
        result.put("data", null);
        return result;
    }
}
