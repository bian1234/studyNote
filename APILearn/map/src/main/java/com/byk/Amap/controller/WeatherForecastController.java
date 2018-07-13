package com.byk.Amap.controller;

import com.byk.Amap.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @Description:todo
 * @author:ykbian
 * @date:2018/7/12 23:33
 */
@Controller
public class WeatherForecastController {

    @Autowired
    private WeatherService weatherService;

    @Value("${com.byk.weather.cityId01}")
    private String cityId01;

    @Value("${com.byk.weather.number01}")
    private String number01;

    @Value("${com.byk.weather.cityId02}")
    private String cityId02;

    @Value("${com.byk.weather.number02}")
    private String number02;

    @Value("${com.byk.weather.cityId03}")
    private String cityId03;

    @Value("${com.byk.weather.number03}")
    private String number03;

    @Value("${com.byk.weather.cityId04}")
    private String cityId04;

    @Value("${com.byk.weather.number04}")
    private String number04;

    //为了避免接口调用的频率过快，设定一秒钟调用一次
    @Scheduled(cron = "0 0 7 * * ? ")
    private void send01(){
        weatherService.sendWeatherBySMS01(cityId01,number01);
    }


    @Scheduled(cron = "59 59 6 * * ? ")
    private void send02(){
        weatherService.sendWeatherBySMS01(cityId02,number02);
    }

    @Scheduled(cron = "1 0 7 1/1 * ? ")
    private void send03(){
        weatherService.sendWeatherBySMS01(cityId03,number03);
    }

    @Scheduled(cron = "5 0 7 14,15,16,17,18,19,20 7 ? ")
    private void send04(){
        weatherService.sendWeatherBySMS01(cityId04,number04);
    }
}
