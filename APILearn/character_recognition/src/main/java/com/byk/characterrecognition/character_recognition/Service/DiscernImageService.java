package com.byk.characterrecognition.character_recognition.Service;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/19 18:39
 * @Todo:
 */

public interface DiscernImageService {

    public Map discernImage(String image,boolean detect_direction,String detect_language);
}
