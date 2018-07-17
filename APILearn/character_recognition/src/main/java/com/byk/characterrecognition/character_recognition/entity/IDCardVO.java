package com.byk.characterrecognition.character_recognition.entity;

import org.springframework.stereotype.Component;

/**
 * @Description:身份证双面信息信息
 * @author:ykbian
 * @date:2018/7/17 22:30
 */
@Component
public class IDCardVO {


    private  IDCardFront IDCardFront;

    private  IDCardBlack IDCardBlack;

    public com.byk.characterrecognition.character_recognition.entity.IDCardFront getIDCardFront() {
        return IDCardFront;
    }

    public void setIDCardFront(com.byk.characterrecognition.character_recognition.entity.IDCardFront IDCardFront) {
        this.IDCardFront = IDCardFront;
    }

    public com.byk.characterrecognition.character_recognition.entity.IDCardBlack getIDCardBlack() {
        return IDCardBlack;
    }

    public void setIDCardBlack(com.byk.characterrecognition.character_recognition.entity.IDCardBlack IDCardBlack) {
        this.IDCardBlack = IDCardBlack;
    }
}
