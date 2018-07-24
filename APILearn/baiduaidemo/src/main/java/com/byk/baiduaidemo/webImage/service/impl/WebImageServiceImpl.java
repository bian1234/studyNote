package com.byk.baiduaidemo.webImage.service.impl;

import com.byk.baiduaidemo.common.util.AccessTokenUtil;
import com.byk.baiduaidemo.common.util.Base64Util;
import com.byk.baiduaidemo.common.util.FileUtil;
import com.byk.baiduaidemo.common.util.HttpUtil;
import com.byk.baiduaidemo.webImage.service.WebImageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 16:31
 * @Todo:
 */
@Service
public class WebImageServiceImpl  implements WebImageService {



    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 16:34
     *@Description:  识别网络图片文字
     *@param:       图片的路径
    */
    @Override
    public JSONArray readImage(String imageName,String accessToken) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
        // 本地图片路径
        try {
            byte[] imgData = FileUtil.readFileByBytes(imageName);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            JSONObject jsonData = HttpUtil.post(otherHost, accessToken, params);
            JSONArray wordsResult = jsonData.getJSONArray("words_result");
            return  wordsResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
