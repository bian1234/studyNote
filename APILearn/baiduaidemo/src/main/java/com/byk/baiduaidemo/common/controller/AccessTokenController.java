package com.byk.baiduaidemo.common.controller;

import com.byk.baiduaidemo.common.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:13
 * @Todo:   token获取的控制器
 */
@Controller
public class AccessTokenController {

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 15:13
     *@Description: 定时刷新获取token权鉴=======百度要求一个月更新一次
     *              只是定时每月刷新两次，因此不需要返回值，需要使用的地方只需要调用服务即可，不用处理请求
     *              将这个token存在redis中
     *@param:
    */
    @Scheduled(cron = "0 0 0 1,16 * ? ")
    public void getToken(){
        Map map = accessTokenService.getToken();
    }
}
