package com.byk.characterrecognition.character_recognition.Service;

import com.byk.characterrecognition.character_recognition.entity.IDCardBlack;
import com.byk.characterrecognition.character_recognition.entity.IDCardFront;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/16 15:41
 * @Todo:   身份证识别的服务接口
 */

public interface IDCardRecognitionService {


    /**
     * 识别人像面
     * */
    public   IDCardFront IDCardRecognitionFront(String imageUrl, String clientId, String clientSecret);

    /**
     * 识别国徽面
     * */
    public IDCardBlack IDCardRecognitionBlack(String imageUrl, String clientId, String clientSecret);
}
