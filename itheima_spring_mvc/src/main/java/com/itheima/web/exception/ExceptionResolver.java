package com.itheima.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ziyin
 @create 2019-06-2019/6/26-9:24
 */
@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler({Throwable.class})
    public void catchException(Throwable ex){
        System.out.println(ex);
        System.out.println(ex.getMessage());
        System.out.println(ex.getStackTrace());

    }

}
