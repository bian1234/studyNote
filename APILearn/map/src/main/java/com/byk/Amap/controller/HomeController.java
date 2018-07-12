package com.byk.Amap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:todo
 * @author:ykbian
 * @date:2018/7/12 23:43
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public  String toIdex(){
        return "index.html";
    }

    @GetMapping("/readme")
    public  String readMe(){
        return "readme.html";
    }
}
