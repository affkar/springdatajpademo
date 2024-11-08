package com.example.springbatchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbatchdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchdemoApplication.class, args);
	}

}
