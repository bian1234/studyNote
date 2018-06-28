package com.byk.config_learn.controller;

import com.byk.config_learn.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bianyakun
 * @Date: 2018/6/28 13:49
 * @Todo:   为了不和之前的HomeController混淆产生不必要的麻烦，新建一个controlelr
 */
@EnableConfigurationProperties({People.class})
@RestController
public class PeopleContrller {


    @Autowired
    People people;

    @RequestMapping("/people")
    public String people(){
        return people.getMsg()+",我今年"+people.getAge()+",我的学号是"+people.getNum()+",我的希望薪资是"+people.getSalary();
    }
}
