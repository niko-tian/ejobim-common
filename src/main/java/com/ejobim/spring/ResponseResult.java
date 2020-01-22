package com.ejobim.spring;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务响应结果
 *
 * @author zch
 */
@ApiModel(description = "响应结果")
public class ResponseResult<T> {

    /**
     * 构造函数
     * @param root 返回对象
     */
    public ResponseResult(T root) {
        this.success = true;
        this.root = root;
    }

    /**
     * 构造函数
     */
    public ResponseResult() {
        this.success = true;
    }

    /**
     * 错误结果
     * @param errorType 错误类型
     * @param errorCode 错误代码
     * @param errorMessage 错误消息
     * @return 
     */
    public static ResponseResult error(ErrorType errorType, Integer errorCode, String errorMessage) {
        ResponseResult result = new ResponseResult();
        result.success = false;
        result.errorType = errorType;
        result.errorCode = errorCode;
        result.errorMessage = errorMessage;
        return result;
    }

    /**
     * 错误结果
     * @param errorType 错误类型
     * @param errorMessage 错误消息
     * @return 
     */
    public static ResponseResult error(ErrorType errorType, String errorMessage) {
        return error(errorType, null, errorMessage);
    }

    /**
     * 是否成功 如果为true，则errorType及errorMessage为空
     */
    @ApiModelProperty(value = "是否成功")
    public boolean success;

    /**
     * 返回对象
     */
    @ApiModelProperty(value = "返回对象")
    public T root;

    /**
     * 错误类型
     */
    @ApiModelProperty(value = "错误类型，仅当success为false时有效")
    public ErrorType errorType;

    /**
     * 错误代码
     */
    @ApiModelProperty(value = "错误代码，仅当success为false时有效")
    public Integer errorCode;

    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息，仅当success为false时")
    public String errorMessage;

}
