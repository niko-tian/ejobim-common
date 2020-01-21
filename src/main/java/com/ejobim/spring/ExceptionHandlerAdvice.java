package com.ejobim.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理，处理业务异常及其它异常
 * @author zhang
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    @SneakyThrows
    public ResponseResult handleBusinessError(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        ResponseResult result = new ResponseResult();
        result.success = false;
        result.errorType = ex.getErrorType();
        result.errorMessage = ex.getMessage();
        return result;
    }

    @ExceptionHandler(ParameterException.class)
    @SneakyThrows
    public ResponseResult handleParameterError(HttpServletRequest request, HttpServletResponse response, ParameterException ex) {
        ResponseResult result = new ResponseResult();
        result.success = false;
        result.errorType = ErrorType.ParameterError;
        result.errorMessage = ex.getMessage();
        result.root = ex.getErrorMessages();
        return result;
    }

    @ExceptionHandler(Exception.class)
    @SneakyThrows
    public ResponseResult handleError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex.getClass().getName().endsWith("ClientAbortException")) {
            return null;
        }
        ResponseResult result = new ResponseResult();
        result.success = false;
        if ((ex instanceof TypeMismatchException)
                || (ex instanceof MethodArgumentTypeMismatchException)
                || (ex instanceof MethodArgumentNotValidException)
                || (ex instanceof BindException)
                || (ex instanceof ConstraintViolationException)) {
            result.errorType = ErrorType.ParameterError;
            result.errorMessage = "参数错误";
            result.root = ex.getMessage();
        } else {
            this.logError(request, ex);
            result.success = false;
            result.errorType = ErrorType.SystemError;
            result.errorMessage = "系统错误";
        }
        return result;
    }

    private void logError(HttpServletRequest request, Exception ex) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ex.getMessage());
        buffer.append("\n");
        buffer.append(ServletUtils.getInfo(request));
        log.error(buffer.toString(), ex);
    }

}
