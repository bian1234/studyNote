package com.byk.sericefeign.service;

import org.springframework.stereotype.Component;

/**
 * @Author: bianyakun
 * @Date: 2018/6/11 15:25
 * @Todo:
 */
@Component
public class FeignServiceHiHystric  implements  FeignService{


    @Override
    public String helloFeign() {
        return "fegin =======这里出现错误，大概是熔断器的原因";
    }
}
