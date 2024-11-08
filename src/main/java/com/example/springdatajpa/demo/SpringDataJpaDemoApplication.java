package com.example.springdatajpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataJpaDemoApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

}
