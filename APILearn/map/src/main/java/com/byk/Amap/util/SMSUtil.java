package com.byk.Amap.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Map;
/**
 * @Author: bianyakun
 * @Date: 2018/7/5 9:46
 * @Todo:
 */
@Component
public class SMSUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private StringUtil stringUtil;
    /**
     * @Date: 2018/7/5 10:54
     * @todo: 输入一个电话号码，发送短信
     * @param:   * @param null
     */
    public void sendSMS(String phoneNumber,Map map) {
        // 短信应用SDK AppID     // 1400开头
        int appid = 1444444444;
        // 短信应用SDK AppKey
        String appkey = "AppKey";
        // 短信模板ID，需要在短信应用中申请
        int templateId = 156072 ;
        String smsSign = "短信签名";

        String city  = map.get("name").toString();
        String text_day = map.get("text_day").toString();
        String text_night = map.get("text_night").toString();
        String low = map.get("low").toString();
        String high = map.get("high").toString();
        String wind_direction = map.get("wind_direction").toString();
        String wind_scale = map.get("wind_scale").toString();
        String msg = "希望天天开心！";
        //根据不同的天气情况做一些简单的提示
        if (Integer.parseInt(low)+Integer.parseInt(high)/ 2 < 20){
            msg = "记得天冷加衣哦！";
        }if(Integer.parseInt(high) > 30){
            msg = "注意防晒！";
        }if (text_day.contains("雨") || text_night.contains("雨")){
            msg = "下雨记得带伞！";
        }if (Integer.parseInt(wind_scale) > 5){
            msg = "远离危险建筑，小心高空坠物！";
        }if (wind_direction == "无持续风向"){
            wind_direction = "无持续";
        }
        try {
            String[] params = {city,text_day,text_night,low,high,wind_direction,wind_scale,msg};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");
            logger.info(result.toString());
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();

        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
