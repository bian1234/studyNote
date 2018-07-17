package com.byk.characterrecognition.character_recognition.controller;

import com.byk.characterrecognition.character_recognition.Service.IDCardRecognitionService;
import com.byk.characterrecognition.character_recognition.entity.IDCardBlack;
import com.byk.characterrecognition.character_recognition.entity.IDCardFront;
import com.byk.characterrecognition.character_recognition.entity.IDCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bian
 * @Date: 2018/7/16 15:40
 * @Todo:  图片识别的控制类
 */
@RestController
public class CharacterRecognitionContrller {

    @Autowired
    private   IDCardRecognitionService idCardRecognitionService;

    @Autowired
    private IDCardVO idCardVO;

    @Value("${com.byk.baidu.appkey}")
    private String clientId;
    @Value("${com.byk.baidu.secret}")
    private String clientSecret;


    @PostMapping("/discernOnlyFront")
    public void discernOnlyFront(String imageUrl){
        //imageUrl = "F:/test/zheng.jpg";
        IDCardFront idCardFront = idCardRecognitionService.IDCardRecognitionFront(imageUrl,clientId,clientSecret);
    }


    @PostMapping("/discernEntirety")
    public void discernEntirety(String frontUrl,String balckUrl){

        frontUrl = "F:/test/zheng.jpg";
        IDCardFront idCardFront = idCardRecognitionService.IDCardRecognitionFront(frontUrl,clientId,clientSecret);
        IDCardBlack idCardBlack = idCardRecognitionService.IDCardRecognitionBlack(balckUrl,clientId,clientSecret);
        idCardVO.setIDCardBlack(idCardBlack);
        idCardVO.setIDCardFront(idCardFront);
    }
}
