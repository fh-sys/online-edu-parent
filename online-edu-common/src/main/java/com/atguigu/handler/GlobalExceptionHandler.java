package com.atguigu.handler;

import com.atguigu.response.RetVal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: 全局异常控制器
 * @Author Hao
 * @Date 2020/11/3 18:04
 * @Version V1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetVal exceptionHandler(Exception e){
        System.err.println(e.getMessage());
        return RetVal.error().message("出现了全局异常");
    }

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public RetVal MyRuntimeException(MyRuntimeException e){
        System.err.println(e.getMessage());
        return RetVal.error().message("出现了自定义异常");
    }

}
