package com.byk.baiduaidemo.webImage.controller;

import com.byk.baiduaidemo.common.controller.BaseController;
import com.byk.baiduaidemo.common.service.AccessTokenService;
import com.byk.baiduaidemo.webImage.service.WebImageService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 16:30
 * @Todo:
 */
@RestController
public class WebImageController extends BaseController{


    @Autowired
    private WebImageService webImageService;

    @Autowired
    private AccessTokenService accessTokenService;


    /**
     * 图片的路径
     * */
    @Value("${com.byk.image.path}")
    private String imagePath;

    @PostMapping("/discernWebImage")
    public Map discernWebImage(String imageName){
        String accessToken = accessTokenService.getToken().get("access_token").toString();
        String imageUrl = imagePath+imageName;
        JSONArray jsonArray = webImageService.readImage(imageUrl,accessToken);
        return webImageSuccess(jsonArray);
    }

}
