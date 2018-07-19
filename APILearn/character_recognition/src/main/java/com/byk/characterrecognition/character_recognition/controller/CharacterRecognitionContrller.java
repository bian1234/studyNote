package com.byk.characterrecognition.character_recognition.controller;

import com.byk.characterrecognition.character_recognition.Service.IDCardRecognitionService;
import com.byk.characterrecognition.character_recognition.entity.IDCardBlack;
import com.byk.characterrecognition.character_recognition.entity.IDCardFront;
import com.byk.characterrecognition.character_recognition.entity.IDCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/16 15:40
 * @Todo: 身份证识别的控制类
 */
@Controller
@RequestMapping("/baiduAI")
public class CharacterRecognitionContrller extends BaseController{

    @RequestMapping("/index")
    public String tiIndex() {
        return "index.html";
    }

    @Autowired
    private   IDCardRecognitionService idCardRecognitionService;

    @Autowired
    private IDCardVO idCardVO;

    @Value("${com.byk.image.path}")
    private String imagePath;

    @Value("${com.byk.baidu.appkey}")
    private String clientId;
    @Value("${com.byk.baidu.secret}")
    private String clientSecret;


    @PostMapping("/discernOnlyFront")
    public Map discernOnlyFront(String imageName){
        String imageUrl = imagePath+imageName;
        IDCardFront idCardFront = idCardRecognitionService.IDCardRecognitionFront(imageUrl,clientId,clientSecret);
        return querySuccessResponse(idCardFront);
    }


    @PostMapping("/discernEntirety")
    public Map discernEntirety(String imageNameFront,String imageNameBlack){
        String frontUrl = imagePath+imageNameFront;
        String balckUrl = imagePath+imageNameBlack;
        IDCardFront idCardFront = idCardRecognitionService.IDCardRecognitionFront(frontUrl,clientId,clientSecret);
        IDCardBlack idCardBlack = idCardRecognitionService.IDCardRecognitionBlack(balckUrl,clientId,clientSecret);
        idCardVO.setIDCardBlack(idCardBlack);
        idCardVO.setIDCardFront(idCardFront);
        return querySuccessResponse(idCardVO);
    }
}
