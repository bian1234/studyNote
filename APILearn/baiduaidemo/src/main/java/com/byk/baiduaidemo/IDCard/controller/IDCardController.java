package com.byk.baiduaidemo.IDCard.controller;

import com.byk.baiduaidemo.IDCard.service.IDCardService;
import com.byk.baiduaidemo.common.controller.BaseController;
import com.byk.baiduaidemo.common.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:33
 * @Todo: 身份证识别的控制器
 */
@RestController
public class IDCardController extends BaseController{


    /**
     * 图片的路径
     * */
    @Value("${com.byk.image.path}")
    private String imagePath;


    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private IDCardService idCardService;

    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 15:37
     *@Description:  只识别人像面
     *@param:
    */
    @PostMapping("/discernOnlyFront")
    public Map discernOnlyFront(String imageName){
        String accessToken = accessTokenService.getToken().get("access_token").toString();
        String imageUrl = imagePath+imageName;
        Map idCardFront = idCardService.IDCardRecognitionFront(imageUrl,accessToken);
        return querySuccessResponse(idCardFront);
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 15:38
     *@Description: 正反面都识别
     *@param:
    */
    @PostMapping("/discernEntirety")
    public Map discernEntirety(String imageNameFront,String imageNameBlack){
        String accessToken = accessTokenService.getToken().get("access_token").toString();
        String frontUrl = imagePath+imageNameFront;
        String balckUrl = imagePath+imageNameBlack;
        Map idCardFront = idCardService.IDCardRecognitionFront(frontUrl,accessToken);
        Map idCardBlack = idCardService.IDCardRecognitionBlack(balckUrl,accessToken);
        Map IDCard = new HashMap();
        IDCard.put("idCardBlack",idCardBlack);
        IDCard.put("idCardFront",idCardFront);
        return querySuccessResponse(IDCard);
    }

}
