package com.byk.serviceribbon.controller;

import com.byk.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bianyakun
 * @Date: 2018/6/11 9:29
 * @Todo:在controller中用调用HelloService 的方法
 */
@RestController
public class HelloController {


    @Autowired
    HelloService helloService;   //调用service

//
//    @RequestMapping(value = "/hi")
//    public String hi(@RequestParam String name){
//        return helloService.hiService(name);
//    }

    @RequestMapping(value = "/hello")
    public String hi(){
        return helloService.hello();
    }
}
