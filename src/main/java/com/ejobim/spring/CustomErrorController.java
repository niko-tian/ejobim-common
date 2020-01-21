package com.ejobim.spring;

import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义错误控制器，该控制器把其他未处理的错误统一处理为错误响应
 * @author zch
 */
@RequestMapping("")
@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    @ResponseBody
    public String error(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.success = false;
        result.errorType = ErrorType.BadRequest;
        result.errorMessage = "错误请求";
        return JSON.toJSONString(result);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
