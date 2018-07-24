package com.byk.baiduaidemo.IDCard.service.impl;

import com.byk.baiduaidemo.IDCard.service.IDCardService;
import com.byk.baiduaidemo.common.service.AccessTokenService;
import com.byk.baiduaidemo.common.util.AccessTokenUtil;
import com.byk.baiduaidemo.common.util.Base64Util;
import com.byk.baiduaidemo.common.util.FileUtil;
import com.byk.baiduaidemo.common.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:43
 * @Todo:
 */
@Service
public class IDCardServiceImpl  implements IDCardService {




    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 15:46
     *@Description:  人像面识别
     *@param:       图片路径
    */
    @Override
    public Map IDCardRecognitionFront(String imageUrl,String accessToken) {
        // 身份证识别url
        Map IDMap = new HashMap();
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            byte[] imgData = FileUtil.readFileByBytes(imageUrl);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            //解析json数据
            JSONObject jsonData = HttpUtil.post(idcardIdentificate, accessToken, params);
            JSONObject wordsResult = jsonData.getJSONObject("words_result");
            String IdNumber = wordsResult.getJSONObject("公民身份号码").getString("words");
            String gender = wordsResult.getJSONObject("性别").getString("words");
            String nation = wordsResult.getJSONObject("民族").getString("words");
            String name = wordsResult.getJSONObject("姓名").getString("words");
            String address = wordsResult.getJSONObject("住址").getString("words");
            IDMap.put("IdNumber",IdNumber);
            IDMap.put("gender",gender);
            IDMap.put("nation",nation);
            IDMap.put("name",name);
            IDMap.put("address",address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IDMap;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/7/24 16:03
     *@Description:  国徽面识别
     *@param:
    */
    @Override
    public Map IDCardRecognitionBlack(String imageUrl,String accessToken) {
        // 身份证识别url
        Map IDMap = new HashMap();
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            byte[] imgData = FileUtil.readFileByBytes(imageUrl);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=back&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            JSONObject jsonData = HttpUtil.post(idcardIdentificate, accessToken, params);
            JSONObject wordsResult = jsonData.getJSONObject("words_result");
            String issuingAuthority = wordsResult.getJSONObject("签发机关").getString("words");
            String issuingDate = wordsResult.getJSONObject("签发日期").getString("words");
            String expiryDate = wordsResult.getJSONObject("失效日期").getString("words");
            IDMap.put("issuingAuthority",issuingAuthority);
            IDMap.put("issuingDate",issuingDate);
            IDMap.put("expiryDate",expiryDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IDMap;
    }
}
