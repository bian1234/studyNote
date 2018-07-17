package com.byk.characterrecognition.character_recognition.Service.impl;

import com.byk.characterrecognition.character_recognition.Service.TokenService;
import com.byk.characterrecognition.character_recognition.util.AccessTokenUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/16 16:26
 * @Todo:
 */
@Service
public class TokenServiceImpl implements TokenService{


    @Override
    //每月更新两回token
    @Scheduled(cron = "0 0 0 1,16 * ? ")
    public Map getToken(String clientId, String clientSecret) {
        Map token = AccessTokenUtil.getToken(clientId,clientSecret);
        return token;
    }
}
