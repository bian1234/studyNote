package com.byk.sericefeign.controller;

import com.byk.sericefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bianyakun
 * @Date: 2018/6/11 11:25
 * @Todo:
 */

@RestController
public class FeignContrller{

    @Autowired
    FeignService feignService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloFegin(){
        return feignService.helloFeign();
    }
}
