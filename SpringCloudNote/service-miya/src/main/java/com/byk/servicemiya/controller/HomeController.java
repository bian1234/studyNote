package com.byk.servicemiya.controller;

import com.byk.servicemiya.ServiceMiyaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: bian
 * @Date: 2018/6/26 10:48
 * @Todo:
 */
@RestController
public class HomeController {

    private static final Logger LOG = Logger.getLogger(ServiceMiyaApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/miya")
    public String callHome(){
        LOG.log(Level.INFO, "请求 service-miya  ");
        LOG.log(Level.INFO,"又请求 http://localhost:8988/hello ");
        return restTemplate.getForObject("http://localhost:8988/hello", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        LOG.log(Level.INFO, "请求 service-miya ");

        return "i'm service-miya";

    }

}
