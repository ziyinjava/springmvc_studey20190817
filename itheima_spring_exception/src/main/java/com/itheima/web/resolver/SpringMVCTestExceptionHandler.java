package com.itheima.web.resolver;

import com.itheima.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMVCTestExceptionHandler {

    @ExceptionHandler({MyException.class})
    public ModelAndView handleArithmeticException(Exception ex){
        System.out.println("----> 出异常了: " + ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("info", ex);
        return mv;
    }
    
}