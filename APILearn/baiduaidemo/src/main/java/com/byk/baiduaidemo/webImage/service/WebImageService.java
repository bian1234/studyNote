package com.byk.baiduaidemo.webImage.service;

import net.sf.json.JSONArray;

import java.util.Map;

/**
 * @Author: ykbian
 * @Date: 2018/7/24 16:31
 * @Todo:
 */

public interface WebImageService {

    public JSONArray readImage(String imageName, String accessToken);
}
