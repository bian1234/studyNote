package com.byk.characterrecognition.character_recognition.Service.impl;

import com.byk.characterrecognition.character_recognition.Service.IDCardRecognitionService;
import com.byk.characterrecognition.character_recognition.util.AccessTokenUtil;
import com.byk.characterrecognition.character_recognition.util.Base64Util;
import com.byk.characterrecognition.character_recognition.util.FileUtil;
import com.byk.characterrecognition.character_recognition.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/16 15:42
 * @Todo:  身份证识别服务接口的实现类
 */
@Service
public class IDCardRecognitionServiceImpl implements IDCardRecognitionService{


    @Override
    public Map IDCardRecognitionFront(String imageUrl, String clientId, String clientSecret) {

        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 图片路径
        String filePath = imageUrl;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            Map map = AccessTokenUtil.getToken(clientId,clientSecret);
            String accessToken = map.get("access_token").toString();
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
           // Array[]
           // System.out.println(result.);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map IDCardRecognitionBlack(String imageUrl, String clientId, String clientSecret) {
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 本地图片路径
        String filePath =imageUrl;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=back&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            Map map = AccessTokenUtil.getToken(clientId,clientSecret);
            String accessToken = map.get("access_token").toString();
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 本地图片路径
        String filePath ="E:/test/bei.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=back&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            Map map = AccessTokenUtil.getToken("eAoiAib1FQH0BQzIcpVFnhSD","FyRgXxgAE4GfgG92rgPKxjXCbGyvLfYp");
            String accessToken = map.get("access_token").toString();
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



