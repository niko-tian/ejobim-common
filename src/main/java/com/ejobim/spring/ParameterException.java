package com.ejobim.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数异常
 * 可以表示多个参数的多个错误（多个错误用|隔开）
 * @author zch
 */
public class ParameterException extends BusinessException {

    private final Map<String, String> errorMessages = new HashMap<>();

    /**
     * 构造函数
     * @param parameterName 参数名
     * @param message 错误消息
     */
    public ParameterException(String parameterName, String message) {
        super(ErrorType.ParameterError, "参数错误");
        this.errorMessages.put(parameterName, message);
    }
    
    /**
     * 构造函数
     * 几个参数对应一个异常消息
     * @param parameterNames 参数名组
     * @param message 错误消息
     */
    public ParameterException(String[] parameterNames, String message) {
        super(ErrorType.ParameterError, "参数错误");
        for(String parameterName : parameterNames) {
            this.errorMessages.put(parameterName, message);
        }
    }

    /**
     * 增加参数异常
     * @param parameterName 参数名
     * @param message 错误消息
     * @return 异常本身
     */
    public ParameterException add(String parameterName, String message) {
        String original = this.errorMessages.get(parameterName);
        if (original != null) {
            this.errorMessages.put(parameterName, original + "|" + message);
        } else {
            this.errorMessages.put(parameterName, message);
        }
        return this;
    }

    /**
     * 增加参数异常
     * @param ex 异常
     * @return 异常本身
     */
    public ParameterException add(ParameterException ex) {
        for (String key : ex.errorMessages.keySet()) {
            this.add(key, ex.errorMessages.get(key));
        }
        return this;
    }

    /**
     * 获取错误消息
     * @return 参数名与错误消息的映射
     */
    public Map<String, String> getErrorMessages() {
        return Collections.unmodifiableMap(errorMessages);
    }

    /**
     * 增加参数异常
     * @param ex 原异常
     * @param parameterName 参数名
     * @param message 错误消息
     * @return 原异常或新建的异常（如果原异常为null）
     */
    public static ParameterException add(ParameterException ex, String parameterName, String message) {
        if (ex == null) {
            ex = new ParameterException(parameterName, message);
        } else {
            ex.add(parameterName, message);
        }
        return ex;
    }

    /**
     * 增加参数异常
     * @param ex 原异常
     * @param ex2 增加的异常
     * @return 原异常或新建的异常（如果原异常为null）
     */
    public static ParameterException add(ParameterException ex, ParameterException ex2) {
        if (ex == null) {
            return ex2;
        } else {
            ex.add(ex2);
        }
        return ex;
    }
}
