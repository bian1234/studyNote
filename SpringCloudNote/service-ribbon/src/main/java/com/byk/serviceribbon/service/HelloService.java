package com.byk.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: bianyakun
 * @Date: 2018/6/11 9:26
 * @Todo:写一个测试类HelloService，通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，
 * 在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
 * 根据服务实例在请求的时候会用具体的url替换掉服务名
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;//注入RestTemplate

    /**
     * @Author: bianyakun
     * @Date: 2018/6/11 15:14
     * @todo: @HystrixCommand标签是给当前方法增加熔断器，熔断方法指定如果出现错误的话走的方法
     * @param:   * @param null
     */
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(){
        return  restTemplate.getForObject("http://peoduceserver/hello",String.class);
    }

    public String helloError(){
        return  "ribbon ==========不知道为什么就出现这个错误了，可能是服务关闭了";
    }
}
