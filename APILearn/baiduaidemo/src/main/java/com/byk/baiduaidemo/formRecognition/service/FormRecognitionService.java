package com.byk.baiduaidemo.formRecognition.service;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 17:57
 * @Todo:
 */

public interface FormRecognitionService {

        public Map formPost(String formName, String accessToken);

        public Map getResult(String requestId,String resultType,String accessToken);
}
