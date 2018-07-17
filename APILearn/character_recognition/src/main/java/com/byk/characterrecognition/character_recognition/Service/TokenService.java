package com.byk.characterrecognition.character_recognition.Service;

import java.util.Map;

/**
 * @Author: bian
 * @Date: 2018/7/16 16:26
 * @Todo:
 */

public interface TokenService {

    public Map getToken(String clientId,String clientSecret);
}
