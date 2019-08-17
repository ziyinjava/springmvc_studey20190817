package com.itheima.service.impl;

import com.itheima.exception.MyException;
import com.itheima.service.DemoService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author ziyin
 @create 2019-06-2019/6/25-17:05
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void show1() {
        System.out.println("类型转换异常");
        Object str = "zhangsan";
        Integer num = (Integer)str;
    }

    @Override
    public void show2() {
        System.out.println("除零异常");
        int i= 1/0;
    }

    @Override
    public void show3() throws FileNotFoundException {
        System.out.println("文件找不到异常");
        InputStream is = new FileInputStream("c:/xx/xx/xx/sdfs.jpg");
    }

    @Override
    public void show4() {
        String str = null;
        str.length();
    }

    @Override
    public void show5() {
        System.out.println("自定义异常");
        throw new MyException("自定义异常...");
    }
}
