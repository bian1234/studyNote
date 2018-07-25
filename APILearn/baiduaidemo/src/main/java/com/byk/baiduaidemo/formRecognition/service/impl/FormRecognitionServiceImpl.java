package com.byk.baiduaidemo.formRecognition.service.impl;

import com.byk.baiduaidemo.common.util.Base64Util;
import com.byk.baiduaidemo.common.util.FileUtil;
import com.byk.baiduaidemo.common.util.HttpUtil;
import com.byk.baiduaidemo.formRecognition.service.FormRecognitionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 17:58
 * @Todo:
 */
@Service
public class FormRecognitionServiceImpl  implements FormRecognitionService {


    @Override
    public Map formPost(String formName, String accessToken) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";
        Map map = new HashMap();
        try {
            byte[] imgData = FileUtil.readFileByBytes(formName);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            JSONObject jsonData = HttpUtil.post(otherHost, accessToken, params);
            JSONArray jsonArray = jsonData.getJSONArray("result");
            JSONObject requestId = jsonArray.getJSONObject(0);
            String requestId01 = requestId.get("request_id").toString();
            String logId = jsonData.getString("log_id");
            map.put("requestId",requestId01);
            map.put("logId",logId);
            return  map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map getResult(String requestId, String resultType, String accessToken) {

        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/get_request_result";
        Map map = new HashMap();
        try {
            String params = "request_id="+requestId+"&result_type"+resultType;
            JSONObject jsonData = HttpUtil.post(otherHost, accessToken, params);
            JSONObject result = jsonData.getJSONObject("result");
            map.put("result",result);
            return  map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
