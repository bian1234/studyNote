package com.byk.Amap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@Controller
@EnableScheduling
public class AmapApplication extends SpringBootServletInitializer{


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(AmapApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(AmapApplication.class, args);
	}


}
