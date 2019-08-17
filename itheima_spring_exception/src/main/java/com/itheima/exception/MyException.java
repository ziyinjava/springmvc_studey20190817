package com.itheima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ziyin
 @create 2019-06-2019/6/25-17:10
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class MyException extends RuntimeException {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
