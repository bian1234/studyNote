package com.byk.characterrecognition.character_recognition.entity;

import org.springframework.stereotype.Component;

/**
 * @Description:    身份证背面的实体类
 * @author:ykbian
 * @date:2018/7/17 21:15
 */
@Component
public class IDCardBlack {

    private String issuingDate;//签发日期

    private String issuingAuthority;  //签发机关

    private String expiryDate;   //失效日期

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }



    @Override
    public String toString() {
        return "IDCardBlack{" +
                "issuingDate='" + issuingDate + '\'' +
                ", issuingAuthority='" + issuingAuthority + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
