package com.byk.characterrecognition.character_recognition.entity;

import org.springframework.stereotype.Component;

/**
 * @Description:  身份证人像面实体类
 * @author:ykbian
 * @date:2018/7/17 21:24
 */
@Component
public class IDCardFront {

        private String IdNumber;

        private String gender;

        private  String nation;

        private  String name;


        private String address;

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "IDCardFront{" +
                "IdNumber='" + IdNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
