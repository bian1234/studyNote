package com.byk.config_learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bianyakun
 * @Date: 2018/6/28 13:27
 * @Todo:
 */
@RestController
public class HomeController {

    @Value("${byk.name}")
    private String name;

    @Value("${byk.age}")
    private int age;

    @Value("${byk.salary}")
    private String salary;

    @Value("${byk.professional}")
    private String professional;

    @GetMapping(value = "/hello")
    public String hello(){
        return "我是"+name+",我现在"+age+"岁，职业"+professional+",我期望薪资是"+salary;
    }
}
