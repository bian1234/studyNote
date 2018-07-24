package com.byk.baiduaidemo.formRecognition.controller;

import com.byk.baiduaidemo.common.controller.BaseController;
import com.byk.baiduaidemo.common.service.AccessTokenService;
import com.byk.baiduaidemo.formRecognition.service.FormRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 17:54
 * @Todo:   表格文字识别
 *          表格文字识别是一个异步接口，需要先提交请求然后在获取返回结果
 */
@RestController
public class FormRecognitionController extends BaseController {

    /**
     * 图片的路径
     * */
    @Value("${com.byk.image.path}")
    private String imagePath;


    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private FormRecognitionService formRecognitionService;

    @RequestMapping("/readForm")
    public Map readForm(String formName,String type) {
        //提交表格识别的请求
        String accessToken = accessTokenService.getToken().get("access_token").toString();
        String imageUrl = imagePath+formName;
        Map requestMap = formRecognitionService.formPost(imageUrl,accessToken);
        String requestId = requestMap.get("requestId").toString();
        try {
            TimeUnit.MINUTES.sleep(1);//分
            formRecognitionService.getResult(requestId,type,accessToken);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
