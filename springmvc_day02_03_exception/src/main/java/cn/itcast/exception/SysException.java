package cn.itcast.exception;

/**
 * 自定义异常类
 */
public class SysException extends Exception {

    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

}
