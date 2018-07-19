package com.byk.characterrecognition.character_recognition.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/19 18:36
 * @Todo:   网络图片文字识别
 */
@RestController
@RequestMapping("/baiduAI")
public class ImageWordsController extends BaseController{


    @PostMapping("/discernImageWords")
    public Map discernImageWords(){
        return  null;
    }
}
