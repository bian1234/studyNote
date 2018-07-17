package com.byk.characterrecognition.character_recognition.Service;

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
    public Map IDCardRecognitionFront(String imageUrl,String clientId,String clientSecret);

    /**
     * 识别国徽面
     * */
    public Map IDCardRecognitionBlack(String imageUrl,String clientId,String clientSecret);
}
