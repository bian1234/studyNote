package com.byk.baiduaidemo.IDCard.service;

import com.sun.javafx.collections.MappingChange;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:40
 * @Todo:
 */

public interface IDCardService {

    public Map IDCardRecognitionFront(String imageUrl,String accessToken);

    public Map IDCardRecognitionBlack(String imageUrl,String accessToken);
}
