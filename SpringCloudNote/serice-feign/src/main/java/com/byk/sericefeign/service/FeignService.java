package com.byk.sericefeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: bianyakun
 * @Date: 2018/6/11 11:22
 * @Todo:
 */

@FeignClient(value = "peoduceserver",fallback = FeignServiceHiHystric.class)
public interface FeignService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String helloFeign ();
}
