package com.ziyin.mvcframework.controller;

import com.ziyin.mvcframework.annotation.ZZAutowired;
import com.ziyin.mvcframework.annotation.ZZController;
import com.ziyin.mvcframework.annotation.ZZRequestMapping;
import com.ziyin.mvcframework.annotation.ZZRequestParam;
import com.ziyin.mvcframework.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ziyin
 @create 2019-06-2019/6/19-11:24
 */
@ZZController
public class DemoController {

    @ZZAutowired
    private DemoService demoService;

    @ZZRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,@ZZRequestParam("name") String name) {

    }



}
