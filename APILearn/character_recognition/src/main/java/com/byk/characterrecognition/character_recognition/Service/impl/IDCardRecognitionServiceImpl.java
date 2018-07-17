package com.byk.characterrecognition.character_recognition.Service.impl;

import com.byk.characterrecognition.character_recognition.Service.IDCardRecognitionService;
import com.byk.characterrecognition.character_recognition.entity.IDCardBlack;
import com.byk.characterrecognition.character_recognition.entity.IDCardFront;
import com.byk.characterrecognition.character_recognition.entity.IDCardVO;
import com.byk.characterrecognition.character_recognition.util.AccessTokenUtil;
import com.byk.characterrecognition.character_recognition.util.Base64Util;
import com.byk.characterrecognition.character_recognition.util.FileUtil;
import com.byk.characterrecognition.character_recognition.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IDCardFront idCardFront;

    @Autowired
    private IDCardBlack idCardBlack;

    @Override
    public   IDCardFront IDCardRecognitionFront(String imageUrl, String clientId, String clientSecret) {
        // 身份证识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // 图片路径
        try {
            byte[] imgData = FileUtil.readFileByBytes(imageUrl);
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
            idCardFront.setAddress(address);
            idCardFront.setGender(gender);
            idCardFront.setIdNumber(IdNumber);
            idCardFront.setName(name);
            idCardFront.setNation(nation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCardFront;
    }

    @Override
    public IDCardBlack IDCardRecognitionBlack(String imageUrl, String clientId, String clientSecret) {
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
            JSONObject wordsResult = HttpUtil.post(idcardIdentificate, accessToken, params);
            String issuingAuthority = wordsResult.getJSONObject("签发机关").getString("words");
            String issuingDate = wordsResult.getJSONObject("签发日期").getString("words");
            String expiryDate = wordsResult.getJSONObject("失效日期").getString("words");
            idCardBlack.setExpiryDate(expiryDate);
            idCardBlack.setIssuingAuthority(issuingAuthority);
            idCardBlack.setIssuingDate(issuingDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCardBlack;
    }




}



