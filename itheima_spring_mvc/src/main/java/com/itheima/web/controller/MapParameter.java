package com.itheima.web.controller;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mapParameter/")
public class MapParameter {
    
    /**
     * 用Map接收前端提交的Form Data或Query String，如果有相同的参数名，只接收第一个
     * @author XuJijun
     * @param params
     * @return
     */
    @RequestMapping(value = "map")   //一定要带@RequestParam
    public Map<String, Object> test1(@RequestParam Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = params;
        return resultMap;
    }
    
    /**
     * 用MultiValueMap接收前端提交的Form Data或Query String，可以接收相同的参数名的值到同一个list中
     * @author XuJijun
     * @param params
     * @return
     */
    @RequestMapping(value = "multiValueMap")
    public Map<String, List<Object>> test2(@RequestParam MultiValueMap<String, Object> params) {
        Map<String, List<Object>> resultMap = new HashMap<>();
        resultMap = params;
        return resultMap;
    }
    
    /**
     * 用Map接收前端提交的json格式的Request Payload，如果有相同的参数名，只接收最后一个
     * @author XuJijun
     * @param params
     * @return
     */
    @RequestMapping(value = "jsonParams")
    public Map<String, Object> test3(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = params;
        return resultMap;
    }
}