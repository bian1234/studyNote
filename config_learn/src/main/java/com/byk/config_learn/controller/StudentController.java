package com.byk.config_learn.controller;

import com.byk.config_learn.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bianyakun
 * @Date: 2018/6/28 14:05
 * @Todo:
 */
@RestController
//@EnableConfigurationProperties({StudentController.class})
public class StudentController {

    @Autowired
    Student student;

    @RequestMapping("/student")
    public String student(){
        return "我是"+student.getName()+",现在"+student.getAge()+",学号是"+student.getId();
    }
}
