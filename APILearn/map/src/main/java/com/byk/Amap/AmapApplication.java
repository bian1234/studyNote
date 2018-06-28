package com.byk.Amap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class AmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmapApplication.class, args);
	}

	@GetMapping("/")
	public  String toIdex(){
		return "index.html";
	}
}
