package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("testInterceptor执行了...");
        return "success";
    }



    @RequestMapping("/testInterceptor2")
    public String testInterceptor2(){
        System.out.println("testInterceptor2执行了...");
        return "success";
    }

    @RequestMapping("/testInterceptor3/multi/url")
    public String testInterceptor3(){
        System.out.println("testInterceptor3执行了...");
        return "success";
    }
}
