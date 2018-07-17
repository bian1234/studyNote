package com.byk.characterrecognition.character_recognition.controller;

import com.byk.characterrecognition.character_recognition.Service.IDCardRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bian
 * @Date: 2018/7/16 15:40
 * @Todo:  图片识别的控制类
 */
@RestController
public class CharacterRecognitionContrller {

    @Autowired
    private IDCardRecognitionService IDCardRecognitionService;

}
