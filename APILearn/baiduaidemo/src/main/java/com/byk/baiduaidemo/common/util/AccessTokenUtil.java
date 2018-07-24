package com.byk.baiduaidemo.common.util;


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 15:05
 * @Todo:  获取百度AccessToken的工具类
 */

public class AccessTokenUtil {

    public static Map getToken(String clientId,String clientSecret){

        Map token = new HashMap();
        String str = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id="+clientId+"&client_secret="+clientSecret;
        try {
            URL url = new URL(str);
            URLConnection connectionData = url.openConnection();
            connectionData.setConnectTimeout(2000);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            String datas = sb.toString();
            JSONObject jsonData = JSONObject.fromObject(datas);
            token.put("access_token", jsonData.getString("access_token").toString());
            token.put("session_key", jsonData.getString("session_key").toString());
            token.put("scope", jsonData.getString("scope").toString());
            token.put("refresh_token", jsonData.getString("refresh_token").toString());
            token.put("session_secret", jsonData.getString("session_secret").toString());
            token.put("expires_in", jsonData.getString("expires_in").toString());
        }catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }catch (IOException e) {
            System.out.println("IOException");
        }
        return token;
    }


}
