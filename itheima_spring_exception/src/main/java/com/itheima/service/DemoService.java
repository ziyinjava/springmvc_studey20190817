package com.itheima.service;

import java.io.FileNotFoundException;

/**
 * @author ziyin
 @create 2019-06-2019/6/25-17:04
 */
public interface DemoService {
    void show1();

    void show2();

    void show3() throws FileNotFoundException;

    void show4();

    void show5();
}
