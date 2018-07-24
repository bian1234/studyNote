package com.byk.baiduaidemo.common.controller;



import net.sf.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *@Author:      ykbian
 *@date_time:   2018/7/24 15:35
 *@Description:  控制器的基础类，所有的控制器都要走这个控制器
 *@param:
*/
public class BaseController {

    public  Map<String,Object> resultMap;


    /**
     * 参数为空返回值==========也就是图片读取失败
     * @return
     */
    public Map emptyParamResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20001");
        resultMap.put("msg","图片读取失败");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 识别成功
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","处理成功");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 网络图片识别成功
     * @param result
     * @return
     */
    public Map webImageSuccess(JSONArray result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","网络图片识别成功");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 识别失败
     * @return
     */
    public Map failedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50002");
        resultMap.put("msg","处理失败");
        resultMap.put("result",null);
        return resultMap;
    }





}
