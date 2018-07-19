package com.byk.characterrecognition.character_recognition.Service.impl;

import com.byk.characterrecognition.character_recognition.Service.DiscernImageService;
import com.byk.characterrecognition.character_recognition.util.AccessTokenUtil;
import com.byk.characterrecognition.character_recognition.util.Base64Util;
import com.byk.characterrecognition.character_recognition.util.FileUtil;
import com.byk.characterrecognition.character_recognition.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/19 18:43
 * @Todo:
 */
@Service
public class DiscernImageServiceImpl implements DiscernImageService {


    @Override
    public Map discernImage(String image, boolean detect_direction, String detect_language) {
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 图片路径
        try {
            byte[] imgData = FileUtil.readFileByBytes(image);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            Map map = AccessTokenUtil.getToken(clientId,clientSecret);
            String accessToken = map.get("access_token").toString();
            JSONObject wordsResult = HttpUtil.post(idcardIdentificate, accessToken, params);
            String IdNumber = wordsResult.getJSONObject("公民身份号码").getString("words");
            String gender = wordsResult.getJSONObject("性别").getString("words");
            String nation = wordsResult.getJSONObject("民族").getString("words");
            String name = wordsResult.getJSONObject("姓名").getString("words");
            String address = wordsResult.getJSONObject("住址").getString("words");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
