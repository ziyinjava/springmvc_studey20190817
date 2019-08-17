package com.itheima.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyEx {

    @ExceptionHandler
    @ResponseBody
    public String xxx(Exception ex){
        return "发生错误了..错误信息是:"+ex.getMessage();
    }
}
