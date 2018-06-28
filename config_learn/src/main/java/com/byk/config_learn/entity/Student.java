package com.byk.config_learn.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: bianyakun
 * @Date: 2018/6/28 14:02
 * @Todo:
 */
@Configuration
@PropertySource(value = "classpath:my.properties")   //指定配置文件
@ConfigurationProperties(prefix = "student") //属性的前缀
public class Student {

    private String name;

    private int age;

    private  int  id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
