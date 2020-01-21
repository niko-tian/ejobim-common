package com.ejobim.spring;

/**
 * 错误类型
 *
 * @author zch
 */
public enum ErrorType {
    
    /**
     * 错误请求
     */
    BadRequest,
    
    /**
     * 参数错误
     */
    ParameterError,
    
    /**
     * 记录已存在
     */
    RecordExists,
    
    /**
     * 记录不存在
     */
    RecordNotExists,
    
    /**
     * 用户已存在
     */
    UserExists,
    
    /**
     * 用户不存在
     */
    UserNotExists,
    
    /**
     * 密码错误
     */
    PasswordError,
    
    /**
     * 会话过期
     */
    SessionExpired,
    
    /**
     * 未认证
     */
    Unverified,
    
    /**
     * 禁止访问
     */
    AccessDenied,
    
    /**
     * 功能受限
     */
    FunctionLimited,
    
    /**
     * 功能过期
     */
    FunctionExpired,
    
    /**
     * 不能删除
     */
    CanNotDelete,
    
    /**
     * 更新失败
     */
    UpdateFailed,
    
    /**
     * 唯一性冲突
     */
    UniqueConflict,
    
    /**
     * 验证失败
     */
    VerifyFailed,
    
    /**
     * 权限受限
     */
    RightLimited,
    
    /**
     * 余额不足
     */
    LowBalance,
    
    /**
     * 共享冲突
     */
    SharingConflict,
    
    /**
     * 资源未找到
     */
    ResourceNotFound,
    
    /**
     * 系统错误
     */
    SystemError,
    
    /**
     * 无效状态
     */
    InvalidState,
    
    /**
     * 怀疑是机器人
     */
    DoubtRobot,
    
    /**
     * 循环引用
     */
    CircleRef,
    
}
