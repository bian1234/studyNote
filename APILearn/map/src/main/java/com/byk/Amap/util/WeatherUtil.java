package com.byk.Amap.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * java调用新知天气预报接口
 *
 * @author Administrator
 *
 */
@Component
public class WeatherUtil {

    /**
     * 获取一周天气
     * 方 法 名：getWeekWeatherMap
     * @param cityName  城市名称
     */
    public  Map getTodayWeatherMap(String cityName)
            throws IOException, NullPointerException {
        // 连接新知天气的api
        URL url = new URL("https://api.seniverse.com/v3/weather/daily.json?ts=1531466740671&ttl=30&uid=U55CA5FEBC&sig=WaueiPRGgqRvbHwVwY0P5pnYEHs%3D&location="+cityName+"&language=zh-Hans&unit=c&start=0&days=1");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(2000);
        Map map = new HashMap();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            String datas = sb.toString();
            JSONObject jsonData = JSONObject.fromObject(datas);
            //返回的是一个JSONArray，数组形式
            JSONArray results = jsonData.getJSONArray("results");
            //目前只需要当天的天气情况，因此只取出第一个对象即可
            JSONObject result = results.getJSONObject(0);
            //根据对象的"key"值获取数据

            //城市名称信息   存入map
            JSONObject location = result.getJSONObject("location");
            map.put("name", location.getString("name").toString());

            //天气信息     存入map
            JSONArray dailys = result.getJSONArray("daily");
            JSONObject daily = dailys.getJSONObject(0);
            map.put("text_day", daily.getString("text_day").toString());
            map.put("text_night", daily.getString("text_night").toString());
            map.put("low", daily.getString("low").toString());
            map.put("high", daily.getString("high").toString());
            map.put("wind_direction", daily.getString("wind_direction").toString());
            map.put("wind_scale", daily.getString("wind_scale").toString());
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

//
//
//    public static void main(String[] args) {
//        try {
//            //测试获取当天
//            Map map = WeatherUtil.getTodayWeatherMap("beijing");
//            System.out.println(map.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

