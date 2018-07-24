package com.byk.baiduaidemo.common.service.impl;

import com.byk.baiduaidemo.common.service.AccessTokenService;
import com.byk.baiduaidemo.common.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:17
 * @Todo:   获取token信息的实现类
 */
@Service
public class AccessTokenServiceImpl  implements AccessTokenService {



    @Value("${com.byk.baidu.appkey}")
    private String clientId;
    @Value("${com.byk.baidu.secret}")
    private String clientSecret;

    @Override
    public Map getToken() {
        return AccessTokenUtil.getToken(clientId,clientSecret);
    }
}
