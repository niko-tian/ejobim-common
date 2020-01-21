package com.ejobim.spring;

/**
 * 业务异常
 *
 * @author zch
 */
public class BusinessException extends Exception {

    private final ErrorType errorType;

    /**
     * 构造器
     *
     * @param errorType 错误类型
     * @param message 错误消息
     */
    public BusinessException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * 获得错误类型
     *
     * @return 错误类型
     */
    public ErrorType getErrorType() {
        return this.errorType;
    }
}
