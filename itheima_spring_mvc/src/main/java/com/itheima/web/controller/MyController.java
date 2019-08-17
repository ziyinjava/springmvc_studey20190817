package com.itheima.web.controller;

import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @RequestMapping("/regist")
    public String xxx(User user){
        System.out.println(user);
        return "login";
    }


    // ,produces = {"text/html;charset=utf-8","application/json"}
    @RequestMapping(value = "/login")
    @ResponseBody
    public String ooo(String username, String password , HttpServletRequest request){

        if ("tom".equalsIgnoreCase(username)&&"cat".equalsIgnoreCase(password)){
            return "登录成功";
        }
        return "用户名或密码错误";
    }
}
