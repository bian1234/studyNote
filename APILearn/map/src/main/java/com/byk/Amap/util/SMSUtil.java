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
    public void sendSMS(String phoneNumber,Map nowMap,Map fetMap) {
        // 短信应用SDK AppID     // 1400开头
        int appid = 1400101674;
        // 短信应用SDK AppKey
        String appkey = "599072c6b8af69616dcf230c96ee603e";
        // 短信模板ID，需要在短信应用中申请
        int templateId = 151299 ;
        String smsSign = "稚子候门";
        String city  = nowMap.get("city").toString();
        String weather = fetMap.get("weather").toString();

        String temp1 = fetMap.get("temp1").toString();
        String temp2 = fetMap.get("temp2").toString();
        //这里其实是个坑，短信模板上带有“风”字，这里又出现个“风”，所以截取一下，只获取数字即可
        //下次直接修改模板


        String WD = nowMap.get("WD").toString();

        WD = WD.substring(0,WD.length()-1);//str=str.Substring(0,str.Length-i);
        String WS = nowMap.get("WS").toString();
        WS = WS.substring(0,WS.length()-1);
        String msg = "希望天天开心！";
        //获取到的温度信息是18C这样的姓氏，因此先获取里面的数字
        try {
            temp1 = stringUtil.numberIntercept(temp1);
            temp2 = stringUtil.numberIntercept(temp2);
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据不同的天气情况做一些简单的提示
        if ((Integer.parseInt(temp1)+Integer.parseInt(temp2)) / 2 < 15){
            msg = "记得天冷加衣哦！";
        }if(Integer.parseInt(temp1)/Integer.parseInt(temp2) > 30){
            msg = "注意防晒！";
        }if (weather.contains("雨")){
            msg = "下雨记得带伞！";
        }

        try {
            String[] params = {city,weather,temp1,temp2,WD,WS,msg};//参数，验证码为5678，30秒内填写
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
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
