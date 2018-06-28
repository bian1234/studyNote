package com.byk.servicehi.controller;

import com.byk.servicehi.ServiceHiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: bian
 * @Date: 2018/6/25 17:14
 * @Todo:
 */
@RestController
public class HomeController {

    private static final Logger LOG = Logger.getLogger(ServiceHiApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        LOG.log(Level.INFO, "请求 service-hi  ");
        LOG.log(Level.INFO,"又跑去请求 http://localhost:8989/miya");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }
    @RequestMapping("/hello")
    public String info(){
        LOG.log(Level.INFO, "请求 service-hi ");
        return "i'm service-hi";

    }



//   @Bean
//    public AlwaysSampler defaultSampler(){
//        return new AlwaysSampler();
//    }
}
