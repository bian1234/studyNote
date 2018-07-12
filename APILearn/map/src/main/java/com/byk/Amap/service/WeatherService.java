package com.byk.Amap.service;

import com.byk.Amap.util.SMSUtil;
import com.byk.Amap.util.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @Description:todo
 * @author:ykbian
 * @date:2018/7/12 21:59
 */
@Service
public class WeatherService {

    @Autowired
    private WeatherUtil weatherUtil;


    @Autowired
    private SMSUtil smsUtil;



    //根据城市名查询天气信息并发送短信
    public void sendWeatherBySMS01(String cityId,String phoneNumber){
        try {
            Map nowMap = weatherUtil.getTodayWeather1(cityId);
            Map fetMap = weatherUtil.getTodayWeather2(cityId);
            smsUtil.sendSMS(phoneNumber,nowMap,fetMap);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
