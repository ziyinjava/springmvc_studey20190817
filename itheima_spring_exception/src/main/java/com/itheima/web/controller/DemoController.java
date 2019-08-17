package com.itheima.web.controller;

import com.itheima.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

/**
 * @author ziyin
 @create 2019-06-2019/6/25-17:04
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/show1")
    public String show1(String name) throws FileNotFoundException {
        System.out.println("show run..");
        demoService.show1();
        return "index";
    }
    @RequestMapping("/show2")
    public String show2(String name) throws FileNotFoundException {
        System.out.println("show run..");
        demoService.show2();
        return "index";
    }
    @RequestMapping("/show3")
    public String show3(String name) throws FileNotFoundException {
        System.out.println("show run..");
        demoService.show3();
        return "index";
    }
    @RequestMapping("/show4")
    public String show4(String name) throws FileNotFoundException {
        System.out.println("show run..");
        demoService.show4();
        return "index";
    }
    @RequestMapping("/show5")
    public String show5(String name) throws FileNotFoundException {
        System.out.println("show run..");
        demoService.show5();
        return "index";
    }
}
